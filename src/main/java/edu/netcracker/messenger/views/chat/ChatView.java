package edu.netcracker.messenger.views.chat;

import edu.netcracker.messenger.models.chat.Chat;
import edu.netcracker.messenger.models.chat.ChatType;
import edu.netcracker.messenger.models.user.User;
import edu.netcracker.messenger.views.user.UserPublicView;
import edu.netcracker.messenger.views.user.UserView;

import java.util.ArrayList;
import java.util.List;

public class ChatView {
    private final Long id;

    private final ChatType type;

    private String name;

    private final List<UserView> members = new ArrayList<>();

    public ChatView(Chat chat, User user) {
        id = chat.getId();
        type = chat.getChatType();
        name = chat.getChatName();
        for (User member : chat.getMembers()) {
            members.add(new UserPublicView(member));
            if (type.equals(ChatType.PERSONAL) && member != user) {
                name = member.getFirstName() + ' ' + member.getLastName();
            }
        }
    }

    public Long getId() {
        return id;
    }

    public List<UserView> getMembers() {
        return members;
    }

    public ChatType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
