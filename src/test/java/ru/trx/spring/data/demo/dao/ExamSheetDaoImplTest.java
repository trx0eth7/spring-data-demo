package ru.trx.spring.data.demo.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.trx.spring.data.demo.entity.ExamSheet;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alexander Vasiliev
 */
@SpringBootTest
class ExamSheetDaoImplTest {

    @Autowired
    ExamSheetDao examSheetDao;


    @Test
    void insert() {
        // given
        var examSheet = new ExamSheet();
        var name = "Exam sheet " + UUID.randomUUID();

        examSheet.setName(name);

        //when
        var insertion = (Executable) () -> examSheetDao.insert(examSheet);

        //then
        assertDoesNotThrow(insertion);
    }

    @Test
    void findAllByName() {
        // given
        var examSheet = new ExamSheet();
        var name = "Exam sheet " + UUID.randomUUID();

        examSheet.setName(name);
        examSheetDao.insert(examSheet);

        // when
        var examSheets = examSheetDao
                .findAllByName(name);

        // then
        assertEquals(List.of(name), examSheets.stream()
                        .map(ExamSheet::getName)
                        .collect(Collectors.toList()), "Not found exam sheet by name");
    }
}