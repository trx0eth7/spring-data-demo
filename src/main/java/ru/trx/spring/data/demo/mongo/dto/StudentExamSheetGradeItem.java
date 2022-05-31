package ru.trx.spring.data.demo.mongo.dto;

import org.springframework.data.annotation.Id;

/**
 * @author Alexander Vasiliev
 */
public class StudentExamSheetGradeItem {

    @Id
    private String id;

    private String studentId;

    private Integer value;


}
