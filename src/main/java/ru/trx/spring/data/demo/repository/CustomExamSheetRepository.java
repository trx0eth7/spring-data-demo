package ru.trx.spring.data.demo.repository;

/**
 * @author Alexander Vasiliev
 */
public interface CustomExamSheetRepository {

    void deleteStudent(Integer examSheetId, Integer studentId);
}
