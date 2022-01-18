package com.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PlayerExceptionHandler {


    @ResponseBody
    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String playerNotFoundHandler(PlayerNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidDataHandler(InvalidDataException e) {
        return e.getMessage();
    }
}
