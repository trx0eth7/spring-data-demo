package ru.trx.spring.data.demo.repository;

import ru.trx.spring.data.demo.mongo.dto.StudentExamSheetGrade;
import ru.trx.spring.data.demo.mongo.dto.StudentExamSheetGradeItem;

/**
 * @author Alexander Vasiliev
 */
public interface CustomStudentExamSheetGradeMongoRepository {

    StudentExamSheetGrade addToStudentExamSheetGradeItem(String id, StudentExamSheetGradeItem item);
}
