package ru.trx.spring.data.demo.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.trx.spring.data.demo.entity.ExamSheet;
import ru.trx.spring.data.demo.repository.ExamSheetRepository;
import ru.trx.spring.data.demo.rest.exception.ExamSheetNotFoundException;

import java.util.List;

/**
 * @author Alexander Vasiliev
 */
@RestController
@RequestMapping(value = "/sheets")
public class ExamSheetController {

    protected final ExamSheetRepository examSheetRepository;

    public ExamSheetController(ExamSheetRepository examSheetRepository) {
        this.examSheetRepository = examSheetRepository;
    }

    @GetMapping
    public List<ExamSheet> all() {
        return examSheetRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ExamSheet one(@PathVariable Integer id) {
        return examSheetRepository.findById(id)
                .orElseThrow(ExamSheetNotFoundException::new);
    }

    @PutMapping
    public ExamSheet create(@RequestBody String name) {
        var examSheet = new ExamSheet();

        examSheet.setName(name);

        return examSheetRepository.save(examSheet);
    }

    @PostMapping(value = "{id}")
    public void pushStudent(@PathVariable Integer id, @RequestBody Integer studentId) {

    }
}
