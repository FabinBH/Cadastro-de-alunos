package com.project.certification.models.students.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCertificationDTO {
    private String email;
    private String tech;
}
