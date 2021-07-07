package edu.netcracker.messenger.chat;

import edu.netcracker.messenger.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "personal_chats")
public class PersonalChat {

    public PersonalChat() {
    }

    public PersonalChat(Long senderId, Long recipientId, String chatName) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.chatName = chatName;
    }

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "recipient_id")
    private Long recipientId;

//    @OneToMany
//    private List<User> chatMembers = new ArrayList<>(2);

    @Column(name = "creation_date")
    private final LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "chat_name")
    private String chatName;

    public Long getChatId() {
        return chatId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getChatName() { return chatName; }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
}