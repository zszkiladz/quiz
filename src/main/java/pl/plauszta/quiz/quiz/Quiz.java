package pl.plauszta.quiz.quiz;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_id_seq")
    @SequenceGenerator(name = "quiz_id_seq", sequenceName = "quiz_id_seq", allocationSize = 1)
    private Long id;

    private String title;

    private String text;

    @ElementCollection
    private List<String> options;

    @ElementCollection
    private List<Integer> answer = new ArrayList<>();

    @JoinColumn(name = "email", table = "user")
    private String authorEmail;

    public Quiz() {

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
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id) &&
                Objects.equals(title, quiz.title) &&
                Objects.equals(text, quiz.text) &&
                Objects.equals(options, quiz.options) &&
                Objects.equals(answer, quiz.answer) &&
                Objects.equals(authorEmail, quiz.authorEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, options, answer, authorEmail);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                ", authorEmail='" + authorEmail + '\'' +
                '}';
    }
}
