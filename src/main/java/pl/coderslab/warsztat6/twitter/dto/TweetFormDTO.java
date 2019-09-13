package pl.coderslab.warsztat6.twitter.dto;

import javax.validation.constraints.Size;

public class TweetFormDTO {
    @Size(max = 140, message = "Maksymalna długość tweet'a to 140 znaków")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
