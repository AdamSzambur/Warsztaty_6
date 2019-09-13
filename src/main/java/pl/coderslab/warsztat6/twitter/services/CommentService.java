package pl.coderslab.warsztat6.twitter.services;

import org.springframework.stereotype.Service;
import pl.coderslab.warsztat6.twitter.dto.CommentFormDTO;
import pl.coderslab.warsztat6.twitter.model.Comment;
import pl.coderslab.warsztat6.twitter.model.Tweet;
import pl.coderslab.warsztat6.twitter.repositories.CommentRepository;
import pl.coderslab.warsztat6.twitter.repositories.TweetRepository;
import pl.coderslab.warsztat6.twitter.repositories.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Service
@Transactional
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private TweetRepository tweetRepository;

    public CommentService(CommentRepository commentRepository,UserRepository userRepository,TweetRepository tweetRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    public Long countCommentsByTweet(Tweet tweet) {
        return commentRepository.countAllByTweet(tweet);
    }

    public List<Comment> getAllCommentsByParentAndTweet(Comment parent, Tweet tweet) {
        return commentRepository.getAllByParentAndTweet(parent, tweet);
    }

    public void addComment(CommentFormDTO newComment, String userEmail) {
        Comment comment = new Comment();
        comment.setUser(userRepository.findByEmail(userEmail));

        if (newComment.getParentId()!=null) {
            comment.setParent(commentRepository.findOne(newComment.getParentId()));
        }
        comment.setText(newComment.getText());
        comment.setTweet(tweetRepository.findOne(newComment.getTweetId()));

        commentRepository.save(comment);
    }
}
