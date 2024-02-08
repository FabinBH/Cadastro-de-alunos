package com.project.certification.models.students.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.certification.models.students.entities.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    
    public Optional<Student> findByEmail(String email);
}
