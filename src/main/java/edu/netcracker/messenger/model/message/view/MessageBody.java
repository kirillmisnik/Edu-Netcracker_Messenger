package edu.netcracker.messenger.model.message.view;

public class MessageBody {
    private String text;

    public MessageBody() {
    }

    public MessageBody(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
