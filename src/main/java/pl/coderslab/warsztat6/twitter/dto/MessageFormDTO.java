package pl.coderslab.warsztat6.twitter.dto;

import javax.validation.constraints.Size;

public class MessageFormDTO {

    private Long senderId;
    private Long recipientId;
    @Size(min=1, message = "Wiadomość musi mieć podany tytuł")
    private String title;
    private String text;



    public MessageFormDTO() {
    }

    public MessageFormDTO(Long senderId, Long recipientId) {
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
