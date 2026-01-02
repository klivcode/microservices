package com.springsec.monotholic_to_microservices_quiz_application.dao;


import com.springsec.monotholic_to_microservices_quiz_application.entities.Questions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {
    List<Questions> getByCategory(String category);

    @Query(
            value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RANDOM()",
            nativeQuery = true
    )
    List<Questions> findRandomQuestionsByCategory(@Param("category") String category, Pageable pageable);
}
