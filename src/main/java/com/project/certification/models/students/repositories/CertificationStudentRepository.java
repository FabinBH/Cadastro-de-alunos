package com.project.certification.models.students.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.certification.models.students.entities.CertificationStudent;

public interface CertificationStudentRepository extends JpaRepository<CertificationStudent, UUID> {

    @Query("SELECT c FROM certifications c INNER JOIN c.student std WHERE std.email = :email AND c.tech = :tech")
    List<CertificationStudent> findByEmailAndTech(String email, String tech);
    
    @Query("SELECT c FROM certifications c ORDER BY c.grade DESC LIMIT 10")
    List<CertificationStudent> findTop10ByOrderByGradeDesc();
}
