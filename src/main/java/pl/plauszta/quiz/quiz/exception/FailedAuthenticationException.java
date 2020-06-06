package pl.plauszta.quiz.quiz.exception;

public class FailedAuthenticationException extends RuntimeException {
    public FailedAuthenticationException(String message) {
        super(message);
    }
}
