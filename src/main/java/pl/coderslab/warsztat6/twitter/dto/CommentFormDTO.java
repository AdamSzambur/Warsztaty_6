package pl.coderslab.warsztat6.twitter.dto;


import javax.validation.constraints.Size;

public class CommentFormDTO {

    private Long tweetId;

    private Long parentId;

    @Size(max=60, message = "Maksymalna długość komentarza to 60 znaków")
    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public CommentFormDTO(Long tweetId) {
        this.tweetId = tweetId;
    }

    public CommentFormDTO() {
    }
}
