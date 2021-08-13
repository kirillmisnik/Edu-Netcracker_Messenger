package edu.netcracker.messenger.model.user.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserAlreadyExistsException extends RuntimeException {

    private final List<String> errorMessage = new ArrayList<>();

    public UserAlreadyExistsException(Map<String, String> errors) {
        for (Map.Entry<String, String> error : errors.entrySet()) {
            errorMessage.add(String.format("User with %s %s already exists", error.getKey(), error.getValue()));
        }
    }

    public List<String> getErrorMessages() {
        return errorMessage;
    }

}
