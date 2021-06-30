package edu.netcracker.messenger.user.controller;

import edu.netcracker.messenger.user.exceptions.UserAlreadyExistsException;
import edu.netcracker.messenger.user.views.UserExceptionView;
import edu.netcracker.messenger.user.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public @ResponseBody
    UserExceptionView userAlreadyExistsExceptionHandler(UserAlreadyExistsException e) {
        return new UserExceptionView(e.getErrorMessages(), HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public @ResponseBody
    UserExceptionView userNotFoundExceptionHandler(UserNotFoundException e) {
        return new UserExceptionView(Collections.singletonList(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
