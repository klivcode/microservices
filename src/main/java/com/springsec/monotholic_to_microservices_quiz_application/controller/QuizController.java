package com.springsec.monotholic_to_microservices_quiz_application.controller;

import com.springsec.monotholic_to_microservices_quiz_application.entities.QuestionWrapper;
import com.springsec.monotholic_to_microservices_quiz_application.entities.Questions;
import com.springsec.monotholic_to_microservices_quiz_application.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> creatingQuiz(@RequestParam String category,
                                               @RequestParam Integer numQ,
                                               @RequestParam String title)
    {
        return quizService.createQuiz(category,numQ,title);
    }


    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
    {
        return quizService.getQuizQuestions(id);
    }

}
