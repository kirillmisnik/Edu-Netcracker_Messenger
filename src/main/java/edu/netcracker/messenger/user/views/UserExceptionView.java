package edu.netcracker.messenger.user.views;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class UserExceptionView {
    private final LocalDateTime timestamp = LocalDateTime.now();

    private final HttpStatus status;

    private final List<String> message;

    public UserExceptionView(List<String> message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getMessage() {
        return message;
    }
}
