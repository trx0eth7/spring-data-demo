package ru.trx.spring.data.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.trx.spring.data.demo.mongo.dto.StudentExamSheetGrade;

import java.util.List;

/**
 * @author Alexander Vasiliev
 */
public interface StudentExamSheetGradeMongoRepository extends MongoRepository<StudentExamSheetGrade, String> {

    List<StudentExamSheetGrade> findAllByExamSheetId(String examSheetId);

}
