package com.springsec.quizapp_questionservice.service;

import com.springsec.quizapp_questionservice.dao.QuestionDao;
import com.springsec.quizapp_questionservice.entities.QuestionWrapper;
import com.springsec.quizapp_questionservice.entities.Questions;
import com.springsec.quizapp_questionservice.entities.Response;
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
        return new  ResponseEntity<>("Questions added successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        Pageable pageable = PageRequest.of(0, numQuestions);
        List<Integer> questions = questionsDao.findRandomQuestionsByCategory(categoryName,pageable);

        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Questions> questions = new ArrayList<>();
        for (Integer id : questionIds) {
            questions.add(questionsDao.findById(id).get());
        }
        for (Questions question : questions) {
            QuestionWrapper wrp= new QuestionWrapper();
            wrp.setId(question.getId());
            wrp.setQuestionTitle(question.getQuestionTitle());
            wrp.setOption1(question.getOption1());
            wrp.setOption2(question.getOption2());
            wrp.setOption3(question.getOption3());
            wrp.setOption4(question.getOption4());
            wrappers.add(wrp);
        }


        return new  ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int rightAnswer = 0;

        for(Response r : responses){
            Questions questions = questionsDao.findById(r.getId()).get();
            if(r.getResponse().equals(questions.getRightAnswer())){
                rightAnswer++;
            }

        }
        return new ResponseEntity<>(rightAnswer,HttpStatus.OK);
    }
    }
