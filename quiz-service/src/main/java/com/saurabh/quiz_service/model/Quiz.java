package com.saurabh.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quiz_id;

    private String title;

    @ElementCollection
    private List<Integer>questions;

}
