package pl.plauszta.quiz.quiz.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class QuizControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(QuizControllerAdvice.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(QuizNotFoundException.class)
    public void handleQuizNotFoundException(QuizNotFoundException quizNotFoundException) {
        log.error("Cannot find the quiz", quizNotFoundException);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NotAnAuthorException.class)
    public void handleNotAnAuthorException(NotAnAuthorException notAnAuthorException) {
        log.error("User cannot delete the quiz", notAnAuthorException);
    }
}
