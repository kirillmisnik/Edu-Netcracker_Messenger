package edu.netcracker.messenger.controllers;

import edu.netcracker.messenger.models.chat.exceptions.ChatAlreadyExistsException;
import edu.netcracker.messenger.views.ExceptionView;
import edu.netcracker.messenger.models.chat.exceptions.ChatNotFoundException;
import edu.netcracker.messenger.models.user.exceptions.UserAlreadyExistsException;
import edu.netcracker.messenger.models.user.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
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
    ExceptionView userAlreadyExistsExceptionHandler(UserAlreadyExistsException e) {
        return new ExceptionView(e.getErrorMessages(), HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ChatAlreadyExistsException.class)
    public @ResponseBody
    ExceptionView personalChatAlreadyExists(ChatAlreadyExistsException e) {
        return new ExceptionView(Collections.singletonList(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public @ResponseBody
    ExceptionView userNotFoundExceptionHandler(UserNotFoundException e) {
        return new ExceptionView(e.getErrorMessages(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public @ResponseBody
    ExceptionView accessDeniedException(AccessDeniedException e) {
        return new ExceptionView(Collections.singletonList(e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ChatNotFoundException.class)
    public @ResponseBody
    ExceptionView chatNotFoundExceptionHandler(ChatNotFoundException e) {
        return new ExceptionView(Collections.singletonList(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
