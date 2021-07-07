package edu.netcracker.messenger.chat.message.view;

import java.time.LocalDateTime;

public class MessageView {
    private final Long senderId;

    private final String text;

    private final Long attachmentId;

    private final String attachmentFilename;

    private final LocalDateTime readDate;

    public MessageView(Long senderId, String text, Long attachmentId, String attachmentFilename, LocalDateTime readDate) {
        this.senderId = senderId;
        this.text = text;
        this.attachmentId = attachmentId;
        this.attachmentFilename = attachmentFilename;
        this.readDate = readDate;
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

    public LocalDateTime getReadDate() {
        return readDate;
    }
}
