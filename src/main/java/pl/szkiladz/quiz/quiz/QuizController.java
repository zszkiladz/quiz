package pl.szkiladz.quiz.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szkiladz.quiz.completedquest.CompletedQuizDto;
import pl.szkiladz.quiz.completedquest.CompletedQuizService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class QuizController {

    private static final Logger log = LoggerFactory.getLogger(QuizController.class);

    private final QuizService quizService;
    private final CompletedQuizService completedQuizService;

    public QuizController(QuizService quizService, CompletedQuizService completedQuizService) {
        this.quizService = quizService;
        this.completedQuizService = completedQuizService;
    }

    @PostMapping(path = "/quizzes")
    public QuizDto addQuiz(@RequestBody @Valid QuizDto quiz) {
        log.info("/api/quizzes Saving: {}", quiz);
        return quizService.save(quiz);
    }

    @GetMapping(path = "/quizzes/{id}")
    public ResponseEntity<QuizDto> getQuiz(@PathVariable long id) {
        log.info("/api/quizzes/{} find by id", id);
        Optional<QuizDto> quiz = quizService.findById(id);
        return ResponseEntity.of(quiz);
    }

    @GetMapping(path = "/quizzes")
    public Page<QuizDto> getAllQuizzes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        log.info("/api/quizzes get all quizzes");
        return quizService.getAllQuiz(page, pageSize, sortBy);
    }

    @PostMapping(
            path = "/quizzes/{id}/solve",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response> solve(@PathVariable long id, @RequestBody SolveRequest request) {
        log.info("/api/quizzes/{}/solve request: {}", id, request);
        final Optional<QuizDto> quizOptional = quizService.findById(id);
        final Optional<Response> response = quizOptional.map(quiz -> createSolveResponse(request.getAnswer(), quiz));
        if (response.isPresent() && response.get().isSuccess()) {
            completedQuizService.addCompletedQuiz(id);
        }
        return ResponseEntity.of(response);
    }

    private Response createSolveResponse(List<Integer> answer, QuizDto quiz) {
        boolean success;
        String feedback;

        if (quiz.getAnswer().size() == answer.size() && quiz.getAnswer().containsAll(answer)) {
            success = true;
            feedback = "Good job!";
        } else {
            success = false;
            feedback = "Wrong answer!";
        }

        Response response = new Response();
        response.setFeedback(feedback);
        response.setSuccess(success);
        return response;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/quizzes/{id}")
    public void delete(@PathVariable long id) {
        log.info("/api/quizzes/{} Deleting quest: {}", id, id);
        quizService.delete(id);
    }

    @GetMapping(path = "/quizzes/completed")
    public Page<CompletedQuizDto> getAllCompletion(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "completedAt") String sortBy) {
        log.info("/api/quizzes/completed get all completion quizzes");
        return completedQuizService.getAll(page, pageSize, sortBy);
    }
}
