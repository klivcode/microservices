package com.springsec.quizapp_quizservice.service;


import com.springsec.quizapp_quizservice.dao.QuizDao;
import com.springsec.quizapp_quizservice.entities.QuestionWrapper;
import com.springsec.quizapp_quizservice.entities.Quiz;
import com.springsec.quizapp_quizservice.entities.Response;
import com.springsec.quizapp_quizservice.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(
            String categoryName,
            Integer numQuestions,
            String title) {

        //  NO Pageable here
        List<Integer> questions =
                quizInterface
                        .getQuestionsForQuiz(categoryName, numQuestions)
                        .getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);

        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quizs = quizDao.findById(id);

        List<Integer> questionIds = quizs.get().getQuestionIds();
        List<QuestionWrapper> questionsForUsers=quizInterface.getQuestionsFromId(questionIds).getBody();

        return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer>score=quizInterface.getScore(responses);
        return score;

    }
}
