package com.springsec.quizapp_quizservice.dao;


import com.springsec.quizapp_quizservice.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
