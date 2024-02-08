package com.project.certification.models.students.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCertificationAnswerDTO {

    private String email;
    private String tech;
    private List<QuestionAnswerDTO> questionsAnswers;
}