package com.springsec.monotholic_to_microservices_quiz_application.controller;

import com.springsec.monotholic_to_microservices_quiz_application.entities.Questions;
import com.springsec.monotholic_to_microservices_quiz_application.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public List<Questions> getAllQuestions()
    {

        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Questions> getQuestionsByCategory( @PathVariable  String category) {
        return questionService.getQuestionsByCategory(category);

    }

    @PostMapping("/addQuestion")
    public String save( @RequestBody Questions questions) {
        questionService.addQuestions(questions);
    }
}
