package com.project.certification.models.students.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "answers_certifications")
public class AnswersCertifications {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "certification_id")
    private UUID certificationID;

    @ManyToOne
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    @JsonBackReference
    private CertificationStudent certificationStudent;

    @Column(name = "student_id")
    private UUID studentID;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @Column(name = "question_id")
    private UUID questionID;

    @Column(name = "answer_id")
    private UUID answerID;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
