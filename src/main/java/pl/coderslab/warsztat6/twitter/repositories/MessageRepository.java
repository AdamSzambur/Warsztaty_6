package pl.coderslab.warsztat6.twitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.warsztat6.twitter.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Long countBySenderId(Long senderId);
    Long countByRecipientId(Long recipientId);
    Long countByRecipientIdAndReaded(Long recipientId, Boolean readed);

    List<Message> findAllBySenderIdOrderByCreatedDesc(Long senderId);
    List<Message> findAllByRecipientIdOrderByCreatedDesc(Long recipientId);



}
