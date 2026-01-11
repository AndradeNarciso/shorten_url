package com.andrade.shorten_url.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.InvalidUrlException;

@ControllerAdvice
public class InvalidUrlExceptionHandler {

    @ExceptionHandler(InvalidUrlException.class)
    public String handlerInvalidException() {
        return "error/invalidUrl";
    }
}
