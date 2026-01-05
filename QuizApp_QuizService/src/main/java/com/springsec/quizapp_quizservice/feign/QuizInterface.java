package com.springsec.quizapp_quizservice.feign;

import com.springsec.quizapp_quizservice.entities.QuestionWrapper;
import com.springsec.quizapp_quizservice.entities.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// name of the service
@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    // generate

    @GetMapping("questions/generate")
    ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @RequestParam("categoryName") String categoryName,
            @RequestParam("numQuestions") Integer numQuestions
    );
    //getQuestions/{questionid}
    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
    //getScore

    @PostMapping("questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
