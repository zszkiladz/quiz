package pl.plauszta.quiz.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizDto {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String text;

    @NotNull
    @Size(min = 2)
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> answer = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String authorEmail;

    public QuizDto() {
    }

    public static QuizDto from(Quiz quiz) {
        final QuizDto dto = new QuizDto();
        dto.id = quiz.getId();
        dto.text = quiz.getText();
        dto.title = quiz.getTitle();
        dto.answer = quiz.getAnswer();
        dto.options = quiz.getOptions();
        dto.authorEmail = quiz.getAuthorEmail();
        return dto;
    }

    public Quiz toEntity() {
        final Quiz quiz = new Quiz();
        quiz.setId(id);
        quiz.setTitle(title);
        quiz.setText(text);
        quiz.setAnswer(answer);
        quiz.setOptions(options);
        quiz.setAuthorEmail(authorEmail);
        return quiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizDto quizDto = (QuizDto) o;
        return Objects.equals(id, quizDto.id) &&
                Objects.equals(title, quizDto.title) &&
                Objects.equals(text, quizDto.text) &&
                Objects.equals(options, quizDto.options) &&
                Objects.equals(answer, quizDto.answer) &&
                Objects.equals(authorEmail, quizDto.authorEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, options, answer, authorEmail);
    }

    @Override
    public String toString() {
        return "QuizDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                ", author=" + authorEmail +
                '}';
    }
}
