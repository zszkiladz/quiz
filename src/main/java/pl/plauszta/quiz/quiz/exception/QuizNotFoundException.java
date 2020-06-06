package pl.plauszta.quiz.quiz.exception;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException(long id) {
        super("Quiz not found. Quiz id: " + id);
    }
}
