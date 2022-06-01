package ru.trx.spring.data.demo.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.trx.spring.data.demo.repository.ExamSheetRepository;
import ru.trx.spring.data.demo.repository.StudentExamSheetGradeMongoRepository;
import ru.trx.spring.data.demo.repository.StudentRepository;
import ru.trx.spring.data.demo.rest.dto.ExamSheetReportDto;
import ru.trx.spring.data.demo.rest.dto.StudentReportDto;
import ru.trx.spring.data.demo.rest.exception.ExamSheetNotFoundException;

import java.util.ArrayList;

import static java.util.Collections.emptyList;

/**
 * @author Alexander Vasiliev
 */
@RestController
@RequestMapping(value = "/report")
public class ReportController {

    protected ExamSheetRepository examSheetRepository;
    protected StudentRepository studentRepository;
    protected StudentExamSheetGradeMongoRepository examSheetGradeMongoRepository;

    public ReportController(ExamSheetRepository examSheetRepository,
                            StudentRepository studentRepository,
                            StudentExamSheetGradeMongoRepository examSheetGradeMongoRepository) {
        this.examSheetRepository = examSheetRepository;
        this.studentRepository = studentRepository;
        this.examSheetGradeMongoRepository = examSheetGradeMongoRepository;
    }

    @GetMapping(value = "/{examSheetId}")
    public ExamSheetReportDto one(@PathVariable Integer examSheetId) {
        var examSheet = examSheetRepository.findById(examSheetId)
                .orElseThrow(ExamSheetNotFoundException::new);
        var examSheetWithGrades = examSheetGradeMongoRepository
                .findAllByExamSheetId(examSheetId);

        if (examSheetWithGrades.isEmpty()) {
            return new ExamSheetReportDto(examSheetId, examSheet.getName(), emptyList());
        }

        var studentExamSheetGrade = examSheetWithGrades.get(0);

        var examSheetReportDto = new ExamSheetReportDto();
        var studentReportDtos = new ArrayList<StudentReportDto>();

        for (var studentGrade : studentExamSheetGrade.getStudentExamSheetGradeItems()) {
            studentRepository.findById(studentGrade.getStudentId())
                    .ifPresent(student -> {
                        var studentReportDto = new StudentReportDto();

                        studentReportDto.setId(student.getId());
                        studentReportDto.setFullName(student.getFirstName() + " " + student.getLastName());
                        studentReportDto.setGrade(studentGrade.getValue());

                        studentReportDtos.add(studentReportDto);
                    });


        }

        examSheetReportDto.setId(examSheetId);
        examSheetReportDto.setName(examSheet.getName());
        examSheetReportDto.setStudents(studentReportDtos);

        return examSheetReportDto;
    }
}
