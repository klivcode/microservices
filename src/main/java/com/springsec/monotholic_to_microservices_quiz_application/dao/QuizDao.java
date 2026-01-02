package com.springsec.monotholic_to_microservices_quiz_application.dao;

import com.springsec.monotholic_to_microservices_quiz_application.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
