package edu.netcracker.messenger.models.chat.exceptions;

public class ChatAlreadyExistsException extends RuntimeException {
    public ChatAlreadyExistsException() {
        super("Personal chat already exists");
    }
}
