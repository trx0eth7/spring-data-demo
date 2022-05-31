package ru.trx.spring.data.demo.mongo.dto;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author Alexander Vasiliev
 */
public class StudentExamSheetGrade {

    @Id
    private String id;

    private String examSheetId;

    private List<StudentExamSheetGradeItem> studentExamSheetGradeItems;

    public StudentExamSheetGrade() {
    }

    public StudentExamSheetGrade(String examSheetId,
                                 List<StudentExamSheetGradeItem> studentExamSheetGradeItems) {
        this.examSheetId = examSheetId;
        this.studentExamSheetGradeItems = studentExamSheetGradeItems;
    }

    public String getId() {
        return id;
    }

    public String getExamSheetId() {
        return examSheetId;
    }

    public List<StudentExamSheetGradeItem> getStudentExamSheetGradeItems() {
        return studentExamSheetGradeItems;
    }
}
