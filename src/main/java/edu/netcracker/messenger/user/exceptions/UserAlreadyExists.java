package edu.netcracker.messenger.user.exceptions;

import java.time.LocalDateTime;

public class UserAlreadyExists extends RuntimeException {
    private final LocalDateTime errorTime = LocalDateTime.now();

    public UserAlreadyExists(String type, String value) {
        super(String.format("User with %s %s already exists", type, value));
    }

    public LocalDateTime getErrorTime() {
        return errorTime;
    }
}
