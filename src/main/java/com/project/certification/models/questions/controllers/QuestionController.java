package com.project.certification.models.questions.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.certification.models.questions.dto.AlternativeResultDTO;
import com.project.certification.models.questions.dto.QuestionResultDTO;
import com.project.certification.models.questions.entities.Alternatives;
import com.project.certification.models.questions.entities.Question;
import com.project.certification.models.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/techs/{tech}")
    public List<QuestionResultDTO> findByTech(@PathVariable String tech) {
        var result = this.questionRepository.findByTech(tech);

        var toMap = result.stream().map(question -> mapToDTO(question)).collect(Collectors.toList());
        return toMap;
    }

    static QuestionResultDTO mapToDTO(Question question) {
        var questionResultDTO = QuestionResultDTO.builder().id(question.getId()).tech(question.getTech()).description(question.getDescription()).build();

        List<AlternativeResultDTO> alternativesResultDTOs = question.getAlternatives().stream().map(alternative -> mapAlternativeDTO(alternative))
        .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOs);
        return questionResultDTO;
    }

    static AlternativeResultDTO mapAlternativeDTO(Alternatives alternatives) {
        return AlternativeResultDTO.builder().id(alternatives.getId()).description(alternatives.getDescription()).build();
    }
}
