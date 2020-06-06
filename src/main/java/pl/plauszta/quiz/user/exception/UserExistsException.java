package pl.plauszta.quiz.user.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String email) {
        super("User already exist. Email: " + email);
    }
}
