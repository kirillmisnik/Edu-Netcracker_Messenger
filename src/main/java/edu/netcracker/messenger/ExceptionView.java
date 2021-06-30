package edu.netcracker.messenger;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionView {
    private final LocalDateTime timestamp = LocalDateTime.now();

    private final HttpStatus status;

    private final List<String> message;

    public ExceptionView(List<String> message, HttpStatus status) {
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
