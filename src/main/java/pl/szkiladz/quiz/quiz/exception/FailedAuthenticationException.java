package pl.szkiladz.quiz.quiz.exception;

public class FailedAuthenticationException extends RuntimeException {
    public FailedAuthenticationException(String message) {
        super(message);
    }
}
