package ru.trx.spring.data.demo.repository;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.trx.spring.data.demo.mongo.StudentExamSheetGrade;
import ru.trx.spring.data.demo.mongo.StudentExamSheetGradeItem;

public class CustomStudentExamSheetGradeMongoRepositoryImpl implements CustomStudentExamSheetGradeMongoRepository {

    protected MongoTemplate template;

    public CustomStudentExamSheetGradeMongoRepositoryImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public StudentExamSheetGrade addToStudentExamSheetGradeItem(String id,
                                                                StudentExamSheetGradeItem item) {
        return template.update(StudentExamSheetGrade.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .apply(new Update().push("studentExamSheetGradeItems", item))
                .withOptions(FindAndModifyOptions.options().returnNew(true))
                .findAndModifyValue();
    }
}