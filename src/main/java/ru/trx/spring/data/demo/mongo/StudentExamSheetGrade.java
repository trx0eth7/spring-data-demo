package ru.trx.spring.data.demo.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Alexander Vasiliev
 */
@Document("grades")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentExamSheetGrade {

    @Id
    private String id;
    private Integer examSheetId;
    private List<StudentExamSheetGradeItem> studentExamSheetGradeItems;
}