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

    public StudentExamSheetGradeItem() {
    }

    public StudentExamSheetGradeItem(String id, String studentId, Integer value) {
        this.id = id;
        this.studentId = studentId;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public Integer getValue() {
        return value;
    }
}
