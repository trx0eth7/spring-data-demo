package ru.trx.spring.data.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.trx.spring.data.demo.entity.ExamSheet;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.data.domain.Sort.Direction.ASC;

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
}