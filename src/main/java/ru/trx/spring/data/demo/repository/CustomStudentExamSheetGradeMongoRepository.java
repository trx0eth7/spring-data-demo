package ru.trx.spring.data.demo.repository;

import ru.trx.spring.data.demo.mongo.StudentExamSheetGrade;
import ru.trx.spring.data.demo.mongo.StudentExamSheetGradeItem;

/**
 * @author Alexander Vasiliev
 */
public interface CustomStudentExamSheetGradeMongoRepository {

    StudentExamSheetGrade addToStudentExamSheetGradeItem(String id, StudentExamSheetGradeItem item);
}