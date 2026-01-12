package com.andrade.shorten_url.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.andrade.shorten_url.exception.InvalidUrlException;

@ControllerAdvice
public class HandlerNonexistEcxeption {

    @ExceptionHandler(InvalidUrlException.class)
    public String handleInvalidUrlException(InvalidUrlException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/invalidUrl";
    }
}
