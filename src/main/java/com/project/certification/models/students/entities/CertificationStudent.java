package com.project.certification.models.students.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "certifications")
public class CertificationStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100)
    private String tech;

    @Column(length = 10)
    private int grade;

    @Column(name = "student_id")
    private UUID studentID;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    //@JsonBackReference
    private Student student;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_certification", insertable = false, updatable = false)
    @JsonManagedReference
    private List<AnswersCertifications> answers;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
