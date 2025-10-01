package com.saurabh.question_service.service;

import com.saurabh.question_service.model.Question;
import com.saurabh.question_service.model.QuestionWrapper;
import com.saurabh.question_service.model.Response;
import com.saurabh.question_service.repo.QuestionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private QuestionRepo repo;
    public QuestionService(QuestionRepo repo){
        this.repo=repo;
    }
    
    public ResponseEntity<List<Question>> getAllQuestion(){
        try {
            return  new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(repo.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<Question>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            repo.save(question);
            return new ResponseEntity<>("success",HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, int numQuestion) {
        List<Integer> questions=repo.findRandomQuestionByCategory(category,numQuestion);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
        List<QuestionWrapper>wrappers=new ArrayList<>();
        List<Question>questions=new ArrayList<>();

        for (Integer id:questionIds){
            questions.add(repo.findById(id).get());
        }
        for (Question q:questions){
            QuestionWrapper w=new QuestionWrapper();
            w.setQuestion_id(q.getQuestion_id());
            w.setQuestionTitle(q.getQuestionTitle());
            w.setOption1(q.getOption1());
            w.setOption2(q.getOption2());
            w.setOption3(q.getOption3());
            w.setOption4(q.getOption4());
            wrappers.add(w);
        }

        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;

        for (Response response:responses){
            Question question=repo.findById(response.getId()).get();
            if (response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
