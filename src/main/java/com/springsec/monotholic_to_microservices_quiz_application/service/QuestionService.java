package com.springsec.monotholic_to_microservices_quiz_application.service;

import com.springsec.monotholic_to_microservices_quiz_application.dao.QuestionDao;
import com.springsec.monotholic_to_microservices_quiz_application.entities.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionsDao;

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try
        {
            return new ResponseEntity<>(questionsDao.findAll(), HttpStatus.OK);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionsDao.getByCategory(category), HttpStatus.OK);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestions(Questions questions) {
        questionsDao.save(questions);
        return new  ResponseEntity<>("Questions added successfully", HttpStatus.OK);
    }
}