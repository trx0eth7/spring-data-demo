package ru.trx.spring.data.demo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexander Vasiliev
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamSheetReportDto implements Serializable {

    private Integer id;
    private String name;
    private List<StudentReportDto> students;
}
