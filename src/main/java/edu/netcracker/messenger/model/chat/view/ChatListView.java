package edu.netcracker.messenger.model.chat.view;

public class ChatListView {
    private final Long chatId;
    private final String chatName;
    private final Long chatPictureId;
    private final String lastMessage;

    public ChatListView(Long chatId, String chatName, Long chatPictureId, String lastMessage) {
        this.chatId = chatId;
        this.chatName = chatName;
        this.chatPictureId = chatPictureId;
        this.lastMessage = lastMessage;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public Long getChatPictureId() {
        return chatPictureId;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
