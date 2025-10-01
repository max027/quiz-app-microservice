package com.saurabh.question_service.controller;

import com.saurabh.question_service.model.Question;
import com.saurabh.question_service.model.QuestionWrapper;
import com.saurabh.question_service.model.Response;
import com.saurabh.question_service.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private QuestionService service;

    @GetMapping("allQuestion")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return service.getAllQuestion() ;
    }

    public QuestionController(QuestionService service){
            this.service=service;
    }

    @GetMapping("category/{tophic}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String tophic){
        return service.getQuestionByCategory(tophic);
    }

    @PostMapping("add")
    public  ResponseEntity<String>addQuestion(@RequestBody Question question){
        return service.addQuestion(question);
    }
    @GetMapping("generate")
    public ResponseEntity<List<Integer>>getQuestionForQuiz(@RequestParam String category,@RequestParam int numQuestion){
        return service.getQuestionForQuiz(category,numQuestion);
    }

    @PostMapping("getQuestion")
    public ResponseEntity<List<QuestionWrapper>>getQuestionFromId(@RequestBody List<Integer>question_ids){
        return service.getQuestionFromId(question_ids);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer>getScore(@RequestBody List<Response>responses){
        return service.getScore(responses);
    }
}
