package ru.trx.spring.data.demo.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alexander Vasiliev
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ExamSheetNotFoundException extends RuntimeException {
    public ExamSheetNotFoundException() {
    }

    public ExamSheetNotFoundException(String message) {
        super(message);
    }

    public ExamSheetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
