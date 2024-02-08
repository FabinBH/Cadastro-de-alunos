package com.project.certification.models.questions.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.certification.models.questions.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    
    List<Question> findByTech(String tech);
    
}
