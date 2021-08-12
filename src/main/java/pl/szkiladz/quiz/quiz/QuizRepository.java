package pl.szkiladz.quiz.quiz;

import org.springframework.data.repository.PagingAndSortingRepository;

interface QuizRepository extends PagingAndSortingRepository<Quiz, Long> {

}