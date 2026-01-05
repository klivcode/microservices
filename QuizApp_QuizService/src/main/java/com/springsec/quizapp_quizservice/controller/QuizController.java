package com.springsec.quizapp_quizservice.controller;


import com.springsec.quizapp_quizservice.entities.Response;
import com.springsec.quizapp_quizservice.entities.dto.QuizDto;
import com.springsec.quizapp_quizservice.entities.QuestionWrapper;
import com.springsec.quizapp_quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> creatingQuiz(@RequestBody QuizDto quizDto)
    {
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
    }


    @PostMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
    {
        return quizService.getQuizQuestions(id);
    }


    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses)
    {
        return quizService.calculateResult(id,responses);
    }
}
