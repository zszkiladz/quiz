package pl.plauszta.quiz.completedquest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

interface CompletedQuizRepository extends PagingAndSortingRepository<CompletedQuiz, Long> {

    Page<CompletedQuiz> findAllByUserName(String currentUserEmail, Pageable paging);
}
