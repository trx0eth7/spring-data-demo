package ru.trx.spring.data.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.trx.spring.data.demo.entity.ExamSheet;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alexander Vasiliev
 */
@SpringBootTest
class ExamSheetRepositoryTest {
    @Autowired
    ExamSheetRepository examSheetRepository;

    @Test
    void findByNameContainingIgnoreCase() {
        // given
        var examSheet = new ExamSheet();
        var examSheetNumber = UUID.randomUUID().toString();

        examSheet.setName("Exam sheet " + examSheetNumber);

        var savedExamSheet = examSheetRepository.save(examSheet);

        // when
        var foundExamSheets = examSheetRepository
                .findAllByNameContainingIgnoreCase(examSheetNumber);

        // then
        assertEquals(List.of(savedExamSheet.getId()), foundExamSheets.stream()
                .map(ExamSheet::getId)
                .collect(Collectors.toList()), "Not found exam sheet by name");
    }
}