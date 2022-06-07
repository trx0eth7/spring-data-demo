package ru.trx.spring.data.demo.repository;

/**
 * @author Alexander Vasiliev
 */
public interface CustomExamSheetRepository {

    /**
     * WARNING:
     * Delete student from all exam sheets
     *
     * @param studentId as is
     */
    void deleteStudent(Integer studentId);
}