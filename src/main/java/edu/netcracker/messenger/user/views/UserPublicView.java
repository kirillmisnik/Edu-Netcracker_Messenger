package edu.netcracker.messenger.user.views;

import edu.netcracker.messenger.user.User;

import java.time.LocalDateTime;

public class UserPublicView {

    private final Long id;

    private final String username;

    private final String firstName;

    private final String lastName;

    private final String bio;

    private final LocalDateTime lastOnlineDate;

    public UserPublicView(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.bio = user.getBio();
        this.lastOnlineDate = user.getLastOnlineDate();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public LocalDateTime getLastOnlineDate() {
        return lastOnlineDate;
    }
}
