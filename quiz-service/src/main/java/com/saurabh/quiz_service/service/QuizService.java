package com.saurabh.quiz_service.service;

import com.saurabh.quiz_service.feign.QuizInterface;
import com.saurabh.quiz_service.model.QuestionWrapper;
import com.saurabh.quiz_service.model.Quiz;
import com.saurabh.quiz_service.model.Response;
import com.saurabh.quiz_service.repo.QuizRepo;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@EnableFeignClients
public class QuizService {

    private QuizRepo repo;


    private QuizInterface quizInterface;

    public QuizService(QuizRepo repo, QuizInterface quizInterface) {
        this.repo = repo;
        this.quizInterface = quizInterface;
    }

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        repo.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz = repo.findById(id).get();
        List<Integer> questionsId = quiz.getQuestions();

        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionFromId(questionsId);


        return questions;
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        ResponseEntity<Integer>score=quizInterface.getScore(responses);
        return score;
    }
}
