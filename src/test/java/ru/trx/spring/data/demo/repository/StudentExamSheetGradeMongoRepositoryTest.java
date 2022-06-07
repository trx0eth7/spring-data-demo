package ru.trx.spring.data.demo.repository;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.trx.spring.data.demo.entity.ExamSheet;
import ru.trx.spring.data.demo.mongo.StudentExamSheetGrade;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alexander Vasiliev
 */
@SpringBootTest
class StudentExamSheetGradeMongoRepositoryTest {

    @Autowired
    StudentExamSheetGradeMongoRepository studentExamSheetGradeMongoRepository;

    @Autowired
    ExamSheetRepository examSheetRepository;

    @Test
    void findAllByExamSheetId() {
        // given
        var examSheet = examSheetRepository.save(new ExamSheet());
        var studentExamSheetGrade = new StudentExamSheetGrade(new ObjectId().toString(), examSheet.getId(), List.of());
        var studentExamSheetGrade2 = new StudentExamSheetGrade(new ObjectId().toString(), examSheet.getId(), List.of());

        var savedStudentExamSheetGrades = studentExamSheetGradeMongoRepository
                .saveAll(List.of(studentExamSheetGrade, studentExamSheetGrade2));

        // when
        var foundStudentExamSheetGrades = studentExamSheetGradeMongoRepository
                .findAllByExamSheetId(examSheet.getId());

        // then
        assertEquals(savedStudentExamSheetGrades.stream()
                        .map(StudentExamSheetGrade::getId)
                        .collect(Collectors.toSet()),
                foundStudentExamSheetGrades.stream()
                        .map(StudentExamSheetGrade::getId)
                        .collect(Collectors.toSet()),
                "Not found student grades");
    }

}