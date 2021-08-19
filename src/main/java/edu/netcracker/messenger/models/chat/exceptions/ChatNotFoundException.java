package edu.netcracker.messenger.models.chat.exceptions;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(Long id) {
        super("Could not find chat with id: " + id);
    }
}
