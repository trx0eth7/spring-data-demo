package ru.trx.spring.data.demo.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * @author Alexander Vasiliev
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentExamSheetGradeItem {

    @Id
    private String id;
    private Integer studentId;
    private Integer value;
}
