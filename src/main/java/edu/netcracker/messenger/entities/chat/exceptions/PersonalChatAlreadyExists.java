package edu.netcracker.messenger.entities.chat.exceptions;

public class PersonalChatAlreadyExists extends RuntimeException {
    public PersonalChatAlreadyExists(Long id) {
        super("Personal chat already exists with user: " + id);
    }
}
