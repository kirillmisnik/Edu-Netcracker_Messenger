package edu.netcracker.messenger.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not find user with id: " + id);
    }

    public UserNotFoundException() {super("No users found");}
}
