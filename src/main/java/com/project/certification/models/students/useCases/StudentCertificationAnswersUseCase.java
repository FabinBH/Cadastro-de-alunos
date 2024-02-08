package com.project.certification.models.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.certification.models.questions.entities.Question;
import com.project.certification.models.questions.repositories.QuestionRepository;
import com.project.certification.models.students.dto.StudentCertificationAnswerDTO;
import com.project.certification.models.students.dto.VerifyCertificationDTO;
import com.project.certification.models.students.entities.AnswersCertifications;
import com.project.certification.models.students.entities.CertificationStudent;
import com.project.certification.models.students.entities.Student;
import com.project.certification.models.students.repositories.CertificationStudentRepository;
import com.project.certification.models.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;
    
    @Autowired
    private VerifyCertificationUseCase verifyCertificationUseCase;

    public CertificationStudent execute(StudentCertificationAnswerDTO dto) throws Exception {

        var hasCertification = this.verifyCertificationUseCase.execute(new VerifyCertificationDTO(dto.getEmail(), dto.getTech()));

        if  (hasCertification) {
            throw new Exception("Você já tirou sua certificação!");
        }
        
        List<Question> questions = questionRepository.findByTech(dto.getTech());
        List<AnswersCertifications> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionsAnswers().stream().forEach(questionAnswer -> {
            var question = questions.stream().filter(q -> q.getId().equals(questionAnswer.getQuestionID()))
            .findFirst().get();

            var findCorrect = question.getAlternatives().stream().filter(alternative -> alternative.isCorrect()).findFirst().get();

            if (findCorrect.getId().equals(questionAnswer.getAlternativeID())) {
                questionAnswer.setCorrect(true);
                correctAnswers.incrementAndGet();
            } else {
                questionAnswer.setCorrect(false);
            }

            var answersCertification = AnswersCertifications.builder().answerID(questionAnswer.getAlternativeID()).questionID(questionAnswer.getQuestionID())
            .isCorrect(questionAnswer.isCorrect()).build();

            answersCertifications.add(answersCertification);
        });

        var student = studentRepository.findByEmail(dto.getEmail());

        UUID studentID;

        if (student.isEmpty()) {
            var studentCreated = Student.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }

        CertificationStudent certificationStudent = CertificationStudent.builder().tech(dto.getTech()).studentID(studentID).grade(correctAnswers.get()).build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudent);
        
        answersCertifications.stream().forEach(answerCertification -> {
            answerCertification.setCertificationID(certificationStudent.getId());
            answerCertification.setCertificationStudent(certificationStudent);
        });

        certificationStudent.setAnswers(answersCertifications);

        return certificationStudentCreated;
    }
}
