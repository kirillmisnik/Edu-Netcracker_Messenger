package edu.netcracker.messenger;

import edu.netcracker.messenger.user.exceptions.UserAlreadyExistsException;
import edu.netcracker.messenger.user.exceptions.UserNotFoundException;
import edu.netcracker.messenger.user.views.ExceptionView;
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
    ExceptionView userAlreadyExistsExceptionHandler(UserAlreadyExistsException e) {
        return new ExceptionView(e.getErrorMessages(), HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public @ResponseBody
    ExceptionView userNotFoundExceptionHandler(UserNotFoundException e) {
        return new ExceptionView(Collections.singletonList(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
