package com.saurabh.quiz_service.feign;

import com.saurabh.quiz_service.model.QuestionWrapper;
import com.saurabh.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/generate")
    ResponseEntity<List<Integer>>getQuestionForQuiz(@RequestParam String category, @RequestParam int numQuestion);

    @PostMapping("question/getQuestion")
    ResponseEntity<List<QuestionWrapper>>getQuestionFromId(@RequestBody List<Integer>question_ids);

    @PostMapping("question/getScore")
    ResponseEntity<Integer>getScore(@RequestBody List<Response>responses);
}
