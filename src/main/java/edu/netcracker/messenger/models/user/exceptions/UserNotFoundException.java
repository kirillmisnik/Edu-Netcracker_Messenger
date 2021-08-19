package edu.netcracker.messenger.models.user.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserNotFoundException extends RuntimeException {
    private List<String> errorMessage = new ArrayList<>();

    public UserNotFoundException(List<Long> errors) {
        for (Long userId : errors) {
            errorMessage.add(String.format("User with id: %d does not exists", userId));
        }
    }

    public UserNotFoundException(Long id) {
        errorMessage = Collections.singletonList("Could not find user with id: " + id);
    }

    public UserNotFoundException() {
        errorMessage = Collections.singletonList("No users found");
    }

    public List<String> getErrorMessages() {
        return errorMessage;
    }
}
