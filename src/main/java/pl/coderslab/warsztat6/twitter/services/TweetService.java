package pl.coderslab.warsztat6.twitter.services;

import org.springframework.stereotype.Service;
import pl.coderslab.warsztat6.twitter.dto.TweetFormDTO;
import pl.coderslab.warsztat6.twitter.model.Comment;
import pl.coderslab.warsztat6.twitter.model.Tweet;
import pl.coderslab.warsztat6.twitter.model.User;
import pl.coderslab.warsztat6.twitter.repositories.CommentRepository;
import pl.coderslab.warsztat6.twitter.repositories.TweetRepository;
import pl.coderslab.warsztat6.twitter.repositories.UserRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TweetService {

    private TweetRepository tweetRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    public TweetService(TweetRepository tweetRepository,UserRepository userRepository, CommentRepository commentRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public void addTweet(TweetFormDTO tweetFormDTO, User user) {
        Tweet tweet = new Tweet();
        tweet.setText(tweetFormDTO.getText());
        tweet.setUser(user);
        tweetRepository.save(tweet);
    }

    public List<Tweet> getTweetsOrderByLastOne(User user, boolean allData) {
        List<Tweet> result;

        if (user == null) {
            result = tweetRepository.getAllByOrderByCreatedDesc();
        } else {
            result = tweetRepository.getAllByUserOrderByCreatedDesc(user);
        }
        result.forEach(t->t.setCommentsNumber(commentRepository.countAllByTweet(t)));

        if (allData) {
            result.forEach(t->t.getComments().size());
        }
        return result;
    }

    public Tweet getTweetById(Long id, boolean allData) {
        Tweet result = tweetRepository.getOne(id);
        result.setCommentsNumber(commentRepository.countAllByTweet(result));
        if (allData) {
            result.setComments(commentRepository.getAllByParentAndTweet(null,result));

            for (Comment comment : result.getComments()) {
                comment.setChildren(getChildrenLoop(comment.getChildren()));
            }
        }
        return result;
    }

    public List<Comment> getChildrenLoop(List<Comment> childrenList) {

        for (Comment child : childrenList) {
              child.setChildren(getChildrenLoop(child.getChildren()));
        }
        return childrenList;
    }

}
