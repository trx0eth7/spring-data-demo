package ru.trx.spring.data.demo.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Alexander Vasiliev
 */
@Data
@NoArgsConstructor
public class StudentReportDto implements Serializable {
    private Integer id;
    private String fullName;
    private Integer grade;
}
