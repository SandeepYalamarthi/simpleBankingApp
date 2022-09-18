package com.sample.commons.simplebankingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SimpleBankingException extends RuntimeException {

    public SimpleBankingException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleBankingException(String message) {
        super(message);
    }


}
