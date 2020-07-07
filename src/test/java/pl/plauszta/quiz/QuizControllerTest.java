package pl.plauszta.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.plauszta.quiz.quiz.QuizDto;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class QuizControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldCreateQuiz(){
        //given
        String userEmail = "user@u.com";
        String password = "password";
        QuizDto quizDto = createQuizDto(userEmail);

        //when


    }

    private QuizDto createQuizDto(String userEmail) {
        QuizDto quiz = new QuizDto();
        quiz.setTitle("quiz");
        quiz.setText("Which colour?");
        quiz.setOptions(List.of("blue", "red", "green"));
        quiz.setAnswer(List.of(2));
        quiz.setAuthorEmail(userEmail);

        return quiz;
    }

}
