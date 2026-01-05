package com.springsec.quizapp_questionservice.controller;


import com.springsec.quizapp_questionservice.entities.QuestionWrapper;
import com.springsec.quizapp_questionservice.entities.Questions;
import com.springsec.quizapp_questionservice.entities.Response;
import com.springsec.quizapp_questionservice.service.QuestionService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    Environment environment;

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions()
    {

        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory( @PathVariable  String category) {
        return questionService.getQuestionsByCategory(category);

    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> save( @RequestBody Questions questions) {
       return questionService.addQuestions(questions);
    }

    // Need to be implemented this three things to make independent
    // generate
//    @GetMapping("generate")
//    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
//                                                             @RequestParam Integer numQuestions)
//    {
//        return questionService.getQuestionsForQuiz(categoryName,numQuestions);
//    }
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuestions(
            @RequestParam String categoryName,
            @RequestParam Integer numQuestions) {

        Pageable pageable = PageRequest.of(0, numQuestions);

        return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

    //getQuestions/{questionid}
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionFromId(questionIds);
    }

    //getScore

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }
}
