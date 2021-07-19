package edu.netcracker.messenger.entities.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chats")
public class Chat {

    public Chat() {}

    public Chat(List<Long> chatMembers) {
        if (chatMembers.size() > 2) {
            chatType = ChatType.GROUP;
        }
        this.chatMembersId = chatMembers;
    }

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @Column(name = "chat_type")
    @Enumerated(EnumType.STRING)
    private ChatType chatType = ChatType.PERSONAL;

    @ElementCollection
    @CollectionTable(name = "chat_members", joinColumns = @JoinColumn(name = "chat_id"))
    @Column(name = "user_id")
    private List<Long> chatMembersId;

    @Column(name = "creation_date")
    private final LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "chat_name")
    private String chatName;

    @Column(name = "chat_picture_id")
    private Long chatPictureId;

    public Long getChatId() {
        return chatId;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public List<Long> getChatMembersId() {
        return chatMembersId;
    }

    @JsonIgnore
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getChatName() {
        return chatName;
    }

    public Long getChatPictureId() {
        return chatPictureId;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setChatPictureId(Long chatPictureId) {
        this.chatPictureId = chatPictureId;
    }
}
