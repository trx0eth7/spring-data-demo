package ru.trx.spring.data.demo.repository;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.trx.spring.data.demo.entity.ExamSheet;
import ru.trx.spring.data.demo.entity.Student;
import ru.trx.spring.data.demo.mongo.StudentExamSheetGrade;
import ru.trx.spring.data.demo.mongo.StudentExamSheetGradeItem;

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

    @Autowired
    StudentRepository studentRepository;

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


    @Test
    void addToStudentExamSheetGradeItem() {
        // given
        var examSheet = examSheetRepository.save(new ExamSheet());
        var student = studentRepository.save(new Student());

        var savedStudentExamSheetGrade = studentExamSheetGradeMongoRepository
                .save(new StudentExamSheetGrade(new ObjectId().toString(), examSheet.getId(), List.of()));

        // when
        var studentExamSheetGradeItem = new StudentExamSheetGradeItem(new ObjectId().toString(), student.getId(), 10);
        var modifiedStudentExamSheetGrade = studentExamSheetGradeMongoRepository
                .addToStudentExamSheetGradeItem(savedStudentExamSheetGrade.getId(), studentExamSheetGradeItem);

        // then
        assertEquals(List.of(studentExamSheetGradeItem.getId()),
                modifiedStudentExamSheetGrade.getStudentExamSheetGradeItems().stream()
                        .map(StudentExamSheetGradeItem::getId)
                        .collect(Collectors.toList()), "Not equals");
    }

}