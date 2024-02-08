package com.project.certification.models.certifications.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.certification.models.certifications.useCases.Top10RankingUseCase;
import com.project.certification.models.students.entities.CertificationStudent;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private Top10RankingUseCase top10RankingUseCase;
    
    @GetMapping("/top-10")
    public List<CertificationStudent> top10() {
        return this.top10RankingUseCase.execute();
    }
}
