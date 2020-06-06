package pl.plauszta.quiz.quiz;

import java.util.List;
import java.util.Objects;

public class SolveRequest {

    private List<Integer> answer;

    public List<Integer> getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolveRequest that = (SolveRequest) o;
        return Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer);
    }

    @Override
    public String toString() {
        return "SolveRequest{" +
                "answer=" + answer +
                '}';
    }
}
