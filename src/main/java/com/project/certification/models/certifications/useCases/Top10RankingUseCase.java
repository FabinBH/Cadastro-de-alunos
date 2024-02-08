package com.project.certification.models.certifications.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.certification.models.students.entities.CertificationStudent;
import com.project.certification.models.students.repositories.CertificationStudentRepository;

@Service
public class Top10RankingUseCase {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;
    
    public List<CertificationStudent> execute() {
        return this.certificationStudentRepository.findTop10ByOrderByGradeDesc();
    }
}
