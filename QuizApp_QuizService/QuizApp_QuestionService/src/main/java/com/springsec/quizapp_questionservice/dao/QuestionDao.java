package com.springsec.quizapp_questionservice.dao;

import com.springsec.quizapp_questionservice.entities.Questions;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {
    List<Questions> getByCategory(String category);

    @Query(
            value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RANDOM()",
            nativeQuery = true
    )
    List<Integer> findRandomQuestionsByCategory(@Param("categoryName") String categoryName, Pageable pageable);
}
