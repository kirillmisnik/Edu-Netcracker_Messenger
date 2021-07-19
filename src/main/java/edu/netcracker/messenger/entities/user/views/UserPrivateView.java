package edu.netcracker.messenger.entities.user.views;

import edu.netcracker.messenger.entities.user.User;

import java.time.LocalDateTime;

public class UserPrivateView extends UserView {

    private final Long id;

    private final String username;

    private final String firstName;

    private final String lastName;

    private final String bio;

    private final String phoneNumber;

    private final String email;

    private final LocalDateTime lastOnlineDate;

    public UserPrivateView(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.bio = user.getBio();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getLastOnlineDate() {
        return lastOnlineDate;
    }
}
