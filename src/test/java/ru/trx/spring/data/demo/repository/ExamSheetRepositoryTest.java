package ru.trx.spring.data.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.trx.spring.data.demo.entity.ExamSheet;
import ru.trx.spring.data.demo.entity.Student;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.data.domain.Sort.Direction.ASC;

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
        assertTrue(studentExamSheets.stream()
                .map(ExamSheet::getId)
                .collect(Collectors.toSet())
                .containsAll(Set.of(examSheet.getId(), examSheet2.getId())), "Not equals");
    }

    @Test
    void findAllByNameContainingIgnoreCaseWithSorting() {
        // given
        var baseNumber = UUID.randomUUID().toString();

        var generatedExamSheets = Stream.generate(ExamSheet::new)
                .peek(e -> e.setName("Exam sheet " + baseNumber))
                .limit(10)
                .collect(Collectors.toList());

        examSheetRepository.saveAll(generatedExamSheets);

        // when
        var foundExamSheets = examSheetRepository
                .findAllByNameContainingIgnoreCase(baseNumber, Sort.by(ASC, "id"));

        // then
        assertEquals(foundExamSheets.stream()
                .sorted(Comparator.comparing(ExamSheet::getId))
                .collect(Collectors.toList()), foundExamSheets, "Not equals");
    }

    @Test
    void findAllByNameContainingIgnoreCaseWithPagination() {
        // given
        var baseNumber = UUID.randomUUID().toString();
        var generatedExamSheets =
                IntStream.range(10, 99).boxed().map(idx -> {
                    var examSheet = new ExamSheet();
                    examSheet.setName(String.format("Exam sheet %s#%d", baseNumber, idx));

                    return examSheet;
                }).collect(Collectors.toList());

        examSheetRepository.saveAll(generatedExamSheets);

        var pageable = PageRequest.of(0, 20)
                .withSort(Sort.by(ASC, "name"));

        // when
        var foundExamSheets = examSheetRepository
                .findAllByNameContainingIgnoreCase(baseNumber, pageable);

        // then
        assertEquals(IntStream.range(10, 30).boxed()
                .map(String::valueOf)
                .collect(Collectors.toList()), foundExamSheets.stream()
                .map(es -> es.getName().substring(48)) // 49 - is position after '#'
                .collect(Collectors.toList()));
    }

    @Test
    void deleteStudentFromExamSheet() {
        // given
        var student = new Student();
        var student2 = new Student();

        var students = studentRepository.saveAll(List.of(student, student2));

        var examSheet = new ExamSheet();
        examSheet.setStudents(students);

        var savedExamSheet = examSheetRepository.save(examSheet);
        var needRemoveStudent = students.get(0);

        // when
        examSheetRepository.deleteStudent(savedExamSheet.getId(), needRemoveStudent.getId());

        // then
        var examSheetStudents = studentRepository.findAllByExamSheetId(savedExamSheet.getId());

        assertEquals(List.of(students.get(1).getId()), examSheetStudents.stream()
                .map(Student::getId)
                .collect(Collectors.toList()), "Not equals");
    }

    @Test
    void findByIdWithStudents() {
        // given
        var student = new Student();
        var student2 = new Student();

        var students = studentRepository.saveAll(List.of(student, student2));

        var examSheet = new ExamSheet();
        examSheet.setStudents(students);

        var savedExamSheet = examSheetRepository.save(examSheet);

        // when
        var foundExamSheet = examSheetRepository
                .findByIdWithStudents(savedExamSheet.getId());

        // then
        assertEquals(foundExamSheet.getStudents().size(), 2);
    }
}