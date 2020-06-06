package pl.plauszta.quiz.completedquest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.plauszta.quiz.quiz.exception.FailedAuthenticationException;

import java.time.LocalDateTime;

@Service
public class CompletedQuizService {

    private static final Logger log = LoggerFactory.getLogger(CompletedQuizService.class);

    private final CompletedQuizRepository completedQuizRepository;

    public CompletedQuizService(CompletedQuizRepository completedQuizRepository) {
        this.completedQuizRepository = completedQuizRepository;
    }


    public void addCompletedQuiz(long id) {
        log.debug("Saving completion quiz. Quiz id: {}", id);
        final String email = getCurrentUserEmail();
        CompletedQuiz completedQuiz = new CompletedQuiz();
        completedQuiz.setQuizId(id);
        completedQuiz.setUserName(email);
        completedQuiz.setCompletedAt(LocalDateTime.now());
        completedQuizRepository.save(completedQuiz);
    }

    private String getCurrentUserEmail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new FailedAuthenticationException("Failed authentication.");
        }
        return authentication.getName();
    }

    public Page<CompletedQuizDto> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        log.debug("Finding all completion quizzes");
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        final String currentUserEmail = getCurrentUserEmail();
        return completedQuizRepository.findAllByUserName(currentUserEmail, paging).map(CompletedQuizDto::from);
    }
}

