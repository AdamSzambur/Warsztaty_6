package pl.coderslab.warsztat6.twitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.warsztat6.twitter.model.Comment;
import pl.coderslab.warsztat6.twitter.model.Tweet;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Long countAllByTweet(Tweet tweet);
    List<Comment> getAllByParentAndTweet(Comment parent, Tweet tweet);

}
