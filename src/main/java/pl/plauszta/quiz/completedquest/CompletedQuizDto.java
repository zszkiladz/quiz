package pl.plauszta.quiz.completedquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class CompletedQuizDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    private Long quizId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userName;

    private LocalDateTime completedAt;

    public static CompletedQuizDto from(CompletedQuiz quiz) {
        final CompletedQuizDto dto = new CompletedQuizDto();
        dto.id = quiz.getId();
        dto.quizId = quiz.getQuizId();
        dto.userName = quiz.getUserName();
        dto.completedAt = quiz.getCompletedAt();
        return dto;
    }

    public CompletedQuiz toEntity() {
        final CompletedQuiz quiz = new CompletedQuiz();
        quiz.setQuizId(quizId);
        quiz.setId(id);
        quiz.setUserName(userName);
        quiz.setCompletedAt(completedAt);
        return quiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        CompletedQuizDto that = (CompletedQuizDto) o;
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
        return "CompletedQuizDto{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", userName='" + userName + '\'' +
                ", completedAt=" + completedAt +
                '}';
    }
}
