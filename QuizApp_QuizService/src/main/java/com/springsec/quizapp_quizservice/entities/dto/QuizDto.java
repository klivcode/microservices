package com.springsec.quizapp_quizservice.entities.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private String categoryName;
    private Integer numQuestions;
    private String title;

}
