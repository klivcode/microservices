package com.springsec.monotholic_to_microservices_quiz_application.service;

import com.springsec.monotholic_to_microservices_quiz_application.entities.Response;
import com.springsec.monotholic_to_microservices_quiz_application.dao.QuestionDao;
import com.springsec.monotholic_to_microservices_quiz_application.dao.QuizDao;
import com.springsec.monotholic_to_microservices_quiz_application.entities.QuestionWrapper;
import com.springsec.monotholic_to_microservices_quiz_application.entities.Questions;
import com.springsec.monotholic_to_microservices_quiz_application.entities.Quiz;
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
    private QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
        Pageable pageable = PageRequest.of(0, numQ);
        List<Questions> questions = questionDao
                .findRandomQuestionsByCategory(category, pageable);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quizs = quizDao.findById(id);
        List<Questions> questionsFromDb= quizs.get().getQuestions();
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();
        for(Questions q : questionsFromDb){
            QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUsers.add(qw);
        }
        return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> respnses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Questions> questionsFromDb = quiz.get().getQuestions();
        int rightAnswer = 0;
        int i=0;
        for(Response r : respnses){
            if(r.getResponse().equals(questionsFromDb.get(i).getRightAnswer())){
                rightAnswer++;
            }
            i++;
        }
        return new ResponseEntity<>(rightAnswer,HttpStatus.OK);
    }
}
