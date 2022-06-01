package ru.trx.spring.data.demo.rest.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.trx.spring.data.demo.entity.Student;
import ru.trx.spring.data.demo.mongo.dto.StudentExamSheetGrade;
import ru.trx.spring.data.demo.mongo.dto.StudentExamSheetGradeItem;
import ru.trx.spring.data.demo.repository.StudentExamSheetGradeMongoRepository;
import ru.trx.spring.data.demo.repository.StudentRepository;
import ru.trx.spring.data.demo.rest.exception.StudentNotFoundException;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Alexander Vasiliev
 */
@RestController
@RequestMapping(value = "/students")
public class StudentController {
    protected StudentRepository studentRepository;
    protected StudentExamSheetGradeMongoRepository studentExamSheetGradeMongoRepository;

    public StudentController(StudentRepository studentRepository,
                             StudentExamSheetGradeMongoRepository studentExamSheetGradeMongoRepository) {
        this.studentRepository = studentRepository;
        this.studentExamSheetGradeMongoRepository = studentExamSheetGradeMongoRepository;
    }

    @GetMapping
    public List<Student> all() {
        return studentRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Student one(@PathVariable Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }

    @PutMapping
    public Student create(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PostMapping(value = "/{id}")
    public void giveStudentGrade(@PathVariable Integer id,
                                 @RequestBody StudentGradeDto studentBody) {
        var studentExamSheetGrade = new StudentExamSheetGrade();
        studentExamSheetGrade.setExamSheetId(studentBody.getExamSheetId());

        var savedStudentExamSheetGrade =
                studentExamSheetGradeMongoRepository.save(studentExamSheetGrade);

        var item = new StudentExamSheetGradeItem();
        item.setStudentId(id);
        item.setValue(studentBody.getGrade());

        studentExamSheetGradeMongoRepository.addToStudentExamSheetGradeItem(savedStudentExamSheetGrade.getId(), item);
    }

    @Data
    @NoArgsConstructor
    static class StudentGradeDto implements Serializable {
        @NotNull
        private Integer examSheetId;
        @NotNull
        private Integer grade;
    }
}
