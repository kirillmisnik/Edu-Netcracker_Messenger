package edu.netcracker.messenger.model.user.views;

import edu.netcracker.messenger.model.user.User;

public class UserChatMemberView {

    private final Long id;

    private final String username;

    private final String firstName;

    private final String lastName;

    private final Long profilePictureId;

    public UserChatMemberView(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.profilePictureId = user.getProfilePictureId();
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

    public Long getProfilePictureId() {
        return profilePictureId;
    }
}
