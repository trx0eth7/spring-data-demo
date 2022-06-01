package ru.trx.spring.data.demo.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alexander Vasiliev
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
