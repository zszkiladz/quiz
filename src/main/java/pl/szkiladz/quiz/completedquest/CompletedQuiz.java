package pl.szkiladz.quiz.completedquest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class CompletedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "completed_quiz_id_seq")
    @SequenceGenerator(name = "completed_quiz_id_seq", sequenceName = "completed_quiz_id_seq", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "id", table = "quiz")
    private Long quizId;

    @JoinColumn(name = "email", table = "user")
    private String userName;

    private LocalDateTime completedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long completedQuizId) {
        this.id = completedQuizId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompletedQuiz that = (CompletedQuiz) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(quizId, that.quizId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(completedAt, that.completedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizId, userName, completedAt);
    }

    @Override
    public String toString() {
        return "CompletedQuiz{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", userName='" + userName + '\'' +
                ", completedAt=" + completedAt +
                '}';
    }
}
