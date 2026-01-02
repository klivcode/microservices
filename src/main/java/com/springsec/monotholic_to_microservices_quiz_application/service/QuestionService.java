package com.springsec.monotholic_to_microservices_quiz_application.service;

import com.springsec.monotholic_to_microservices_quiz_application.dao.QuestionDao;
import com.springsec.monotholic_to_microservices_quiz_application.entities.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionsDao;

    public  List<Questions> getAllQuestions() {
       return questionsDao.findAll();

    }

    public List<Questions> getQuestionsByCategory(String category) {
        return questionsDao.getByCategory(category);
    }

    public String addQuestions(Questions questions) {
        questionsDao.save(questions);
        return "Question added successfully";
    }
}