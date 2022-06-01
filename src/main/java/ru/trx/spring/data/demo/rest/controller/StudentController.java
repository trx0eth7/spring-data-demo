package ru.trx.spring.data.demo.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.trx.spring.data.demo.entity.Student;
import ru.trx.spring.data.demo.repository.StudentRepository;
import ru.trx.spring.data.demo.rest.exception.StudentNotFoundException;

import java.util.List;

/**
 * @author Alexander Vasiliev
 */
@RestController
@RequestMapping(value = "/students")
public class StudentController {
    protected StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
}
