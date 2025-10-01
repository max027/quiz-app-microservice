package com.saurabh.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {
    String category_name;
    Integer numQuestions;
    String title;
}
