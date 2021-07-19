package edu.netcracker.messenger.entities.chat.exceptions;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(Long id) {
        super("Could not find chat with id: " + id);
    }
}
