package com.project.certification.models.questions.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResultDTO {
    
    private UUID id;
    private String tech;
    private String description;

    private List<AlternativeResultDTO> alternatives;
}
