package com.saurabh.quiz_service.service;

import com.saurabh.quiz_service.model.QuestionWrapper;
import com.saurabh.quiz_service.model.Quiz;
import com.saurabh.quiz_service.model.Response;
import com.saurabh.quiz_service.repo.QuizRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private QuizRepo repo;
    public QuizService(QuizRepo repo){
        this.repo=repo;
    }

    public ResponseEntity<String>createQuiz(String category, int numQ,String title){
        List<Integer> questions=;
//        Quiz quiz=new Quiz();
//        quiz.setTitle(title);
//        quiz.setQuestions(questions);
//
//
//        repo.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
//        Optional<Quiz> quiz=repo.findById(id);
//        List<Question>questions=quiz.get().getQuestions();
       List<QuestionWrapper>questionWrappers=new ArrayList<>();
//
//        for(Question q:questions){
//            QuestionWrapper qw=new QuestionWrapper(q.getQuestion_id(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
//           questionWrappers.add(qw);
//        }

        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
//            Quiz quiz=repo.findById(id).get();
//            List<Question>questions=quiz.getQuestions();
            int right=0;
//            int i=0;
//            for (Response response:responses){
//                if (response.getResponse().equals(questions.get(i).getRightAnswer())){
//                    right++;
//                }
//                i++;
//            }

            return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
