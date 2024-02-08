package com.project.certification.models.students.controllers;

import com.project.certification.models.students.dto.StudentCertificationAnswerDTO;
import com.project.certification.models.students.dto.VerifyCertificationDTO;
import com.project.certification.models.students.useCases.StudentCertificationAnswersUseCase;
import com.project.certification.models.students.useCases.VerifyCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyCertificationUseCase verifyCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verify")
    public String verifyCertification(@RequestBody VerifyCertificationDTO verifyCertificationDTO) {
        var result = this.verifyCertificationUseCase.execute(verifyCertificationDTO);
        if (result) {
            return "Você já fez a prova!";
        }
        return "Pode fazer a prova!";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
        try {
            var result = this.studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    } 
}
