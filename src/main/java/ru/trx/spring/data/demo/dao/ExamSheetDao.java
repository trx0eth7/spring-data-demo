package ru.trx.spring.data.demo.dao;

import ru.trx.spring.data.demo.entity.ExamSheet;

import java.util.List;

/**
 * @author Alexander Vasiliev
 */
public interface ExamSheetDao {

    void insert(ExamSheet examSheet);

    List<ExamSheet> findAllByName(String name);
}
