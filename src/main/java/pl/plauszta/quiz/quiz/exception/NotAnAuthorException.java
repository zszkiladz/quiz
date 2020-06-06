package pl.plauszta.quiz.quiz.exception;

public class NotAnAuthorException extends RuntimeException {
    public NotAnAuthorException(long id, String name) {
        super("User is not quiz author. Quiz id: " + id + " User name: " + name);
    }
}
