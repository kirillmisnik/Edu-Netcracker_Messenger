package edu.netcracker.messenger.chat.message;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "user_id")
    private Long senderId;

    @Column(name = "text")
    private String text;

    @Column(name = "attachment_id")
    private Long attachmentId;

    @Column(name = "attachment_filename")
    private String attachmentFilename;

    @Column(name = "creation_date")
    private final LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "read_date")
    private LocalDateTime readDate;

    public Long getMessageId() {
        return messageId;
    }

    public Long getChatId() {
        return chatId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public String getText() {
        return text;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public String getAttachmentFilename() {
        return attachmentFilename;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getReadDate() {
        return readDate;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public void setAttachmentFilename(String attachmentFilename) {
        this.attachmentFilename = attachmentFilename;
    }
}
