package ru.trx.spring.data.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.trx.spring.data.demo.entity.ExamSheet;
import ru.trx.spring.data.demo.entity.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alexander Vasiliev
 */
@SpringBootTest
class ExamSheetRepositoryTest {

    @Autowired
    ExamSheetRepository examSheetRepository;

    @Autowired
    StudentRepository studentRepository;

    @Test
    void findAllByNameContainingIgnoreCase() {
        // given
        var examSheet = new ExamSheet();
        var examSheetNumber = UUID.randomUUID().toString();

        examSheet.setName("Exam sheet " + examSheetNumber);

        var savedExamSheet = examSheetRepository.save(examSheet);

        // when
        var foundExamSheets = examSheetRepository.findAllByNameContainingIgnoreCase(examSheetNumber);

        // then
        assertEquals(List.of(savedExamSheet.getId()), foundExamSheets.stream()
                .map(ExamSheet::getId)
                .collect(Collectors.toList()), "Not equals");
    }

    @Test
    void findAllByStudentId() {
        // given
        var student = studentRepository.save(new Student());
        var examSheets = examSheetRepository
                .saveAll(List.of(new ExamSheet(), new ExamSheet(), new ExamSheet()));

        var examSheet = examSheets.get(0);
        var examSheet2 = examSheets.get(1);

        examSheet.setStudents(List.of(student));
        examSheet2.setStudents(List.of(student));

        examSheetRepository.saveAll(List.of(examSheet, examSheet2));

        // when
        var studentExamSheets = examSheetRepository
                .findAllByStudentId(student.getId());

        // then

        assertEquals(Set.of(examSheet.getId(), examSheet2.getId()),
                studentExamSheets.stream()
                        .map(ExamSheet::getId)
                        .collect(Collectors.toSet()), "Not equals");
    }

    @Test
    void findAllByStudentsBirthdayBetween() {
        // given
        var student = new Student();
        student.setBirthday(LocalDate.of(1995, 4, 7));

        var student2 = new Student();
        student2.setBirthday(LocalDate.of(1989, 2, 24));

        studentRepository.saveAll(List.of(student, student2));

        var examSheets = examSheetRepository
                .saveAll(List.of(new ExamSheet(), new ExamSheet(), new ExamSheet()));

        var examSheet = examSheets.get(0);
        var examSheet2 = examSheets.get(1);
        var examSheet3 = examSheets.get(2);

        examSheet.setStudents(List.of(student, student2));
        examSheet2.setStudents(List.of(student, student2));
        examSheet3.setStudents(List.of(student2));

        examSheetRepository.saveAll(List.of(examSheet, examSheet2));

        // when
        var studentExamSheets = examSheetRepository
                .findAllByStudentsBirthdayBetween(
                        LocalDate.of(1993, 1, 1),
                        LocalDate.of(1998, 1, 1));

        // then
        assertEquals(Set.of(examSheet.getId(), examSheet2.getId()),
                studentExamSheets.stream()
                        .map(ExamSheet::getId)
                        .collect(Collectors.toSet()), "Not equals");
    }
}