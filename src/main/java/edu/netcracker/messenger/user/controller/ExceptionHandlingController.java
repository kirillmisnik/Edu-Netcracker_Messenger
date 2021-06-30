package edu.netcracker.messenger.user.controller;

import edu.netcracker.messenger.user.exceptions.UserAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.TreeMap;

@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserAlreadyExists.class)
    public @ResponseBody
    Map<String, String> handleCustomException(UserAlreadyExists exception) {
        Map<String, String> errorMessage = new TreeMap<>();
        errorMessage.put("time", exception.getErrorTime().toString());
        errorMessage.put("message", exception.getMessage());
        return errorMessage;
    }
}
