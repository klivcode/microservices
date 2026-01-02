package com.springsec.monotholic_to_microservices_quiz_application.dao;


import com.springsec.monotholic_to_microservices_quiz_application.entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {
    List<Questions> getByCategory(String category);
}
