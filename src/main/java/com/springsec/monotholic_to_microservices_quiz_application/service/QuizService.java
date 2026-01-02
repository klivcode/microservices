package com.springsec.monotholic_to_microservices_quiz_application.service;

import com.springsec.monotholic_to_microservices_quiz_application.dao.QuestionDao;
import com.springsec.monotholic_to_microservices_quiz_application.dao.QuizDao;
import com.springsec.monotholic_to_microservices_quiz_application.entities.Questions;
import com.springsec.monotholic_to_microservices_quiz_application.entities.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;





import java.util.List;

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
}
