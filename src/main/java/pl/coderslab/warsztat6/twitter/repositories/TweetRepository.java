package pl.coderslab.warsztat6.twitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.warsztat6.twitter.model.Tweet;
import pl.coderslab.warsztat6.twitter.model.User;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findAllByUserOrderByCreatedDesc(User user);
    List<Tweet> findAllByUserIdOrderByCreatedDesc(Long userId);
    List<Tweet> getAllByOrderByCreatedDesc();

    List<Tweet> getAllByUserOrderByCreatedDesc(User user);
}
