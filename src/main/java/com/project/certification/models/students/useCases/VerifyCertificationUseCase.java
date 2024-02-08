package com.project.certification.models.students.useCases;

import com.project.certification.models.students.dto.VerifyCertificationDTO;
import com.project.certification.models.students.repositories.CertificationStudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyCertificationUseCase {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public boolean execute(VerifyCertificationDTO dto) {
        var result = this.certificationStudentRepository.findByEmailAndTech(dto.getEmail(), dto.getTech());
        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }
}
