package com.saurabh.quiz_service.controller;

import com.saurabh.quiz_service.model.QuestionWrapper;
import com.saurabh.quiz_service.model.QuizDto;
import com.saurabh.quiz_service.model.Response;
import com.saurabh.quiz_service.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private QuizService service;
    public QuizController(QuizService service){
        this.service=service;
    }


    @PostMapping("create")
    public ResponseEntity<String>createQuiz(@RequestBody QuizDto quizDto){
        return service.createQuiz(quizDto.getCategory_name(),quizDto.getNumQuestions(),quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return service.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer>submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){
        return service.calculateResult(id,responses);
    }


}
