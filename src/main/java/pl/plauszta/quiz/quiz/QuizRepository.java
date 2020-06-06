package pl.plauszta.quiz.quiz;

import org.springframework.data.repository.PagingAndSortingRepository;

interface QuizRepository extends PagingAndSortingRepository<Quiz, Long> {

}