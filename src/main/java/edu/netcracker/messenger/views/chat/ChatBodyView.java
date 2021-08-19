package edu.netcracker.messenger.views.chat;

import java.util.List;

public class ChatBodyView {

    private List<Long> chatMembersId;

    private String chatName;

    private Long chatPictureId;

    public ChatBodyView() {
    }

    public ChatBodyView(List<Long> chatMembersId) {
        this.chatMembersId = chatMembersId;
    }

    public ChatBodyView(List<Long> chatMembersId, String chatName, Long chatPictureId) {
        this.chatMembersId = chatMembersId;
        this.chatName = chatName;
        this.chatPictureId = chatPictureId;
    }

    public List<Long> getChatMembersId() {
        return chatMembersId;
    }

    public String getChatName() {
        return chatName;
    }

    public Long getChatPictureId() {
        return chatPictureId;
    }
}
