package pl.szkiladz.quiz.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.szkiladz.quiz.quiz.exception.FailedAuthenticationException;
import pl.szkiladz.quiz.quiz.exception.NotAnAuthorException;
import pl.szkiladz.quiz.quiz.exception.QuizNotFoundException;

import java.util.Optional;

@Service
public class QuizService {

    private static final Logger log = LoggerFactory.getLogger(QuizService.class);

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizDto save(final QuizDto quiz) {
        log.debug("Saving quiz in the database. {}", quiz);
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new FailedAuthenticationException("Failed authentication.");
        }
        quiz.setAuthorEmail(authentication.getName());
        Quiz quizEntity = quizRepository.save(quiz.toEntity());
        return QuizDto.from(quizEntity);
    }

    public void delete(long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isEmpty()) {
            throw new QuizNotFoundException(id);
        }
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new FailedAuthenticationException("Failed authentication.");
        }
        final String authorEmail = quiz.get().getAuthorEmail();
        if (!authorEmail.equals(authentication.getName())) {
            throw new NotAnAuthorException(id, authentication.getName());
        }
        quizRepository.delete(quiz.get());
    }

    public Optional<QuizDto> findById(long id) {
        log.debug("Searching for quiz by id {}", id);
        return quizRepository.findById(id).map(QuizDto::from);
    }

    public Page<QuizDto> getAllQuiz(Integer pageNo, Integer pageSize, String sortBy) {
        log.debug("Finding all quizzes");
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return quizRepository.findAll(paging).map(QuizDto::from);
    }
}
