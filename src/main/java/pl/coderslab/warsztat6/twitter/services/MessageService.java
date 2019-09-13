package pl.coderslab.warsztat6.twitter.services;

import org.springframework.stereotype.Service;
import pl.coderslab.warsztat6.twitter.dto.MessageFormDTO;
import pl.coderslab.warsztat6.twitter.model.Message;
import pl.coderslab.warsztat6.twitter.repositories.MessageRepository;
import pl.coderslab.warsztat6.twitter.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }


    public List<Message> getUserInbox(Long userId) {
        return messageRepository.findAllByRecipientIdOrderByCreatedDesc(userId);
    }

    public List<Message> getUserOutbox(Long userId) {
        return messageRepository.findAllBySenderIdOrderByCreatedDesc(userId);
    }

    public Long countOfUnreadMesseges(Long userId) {
        return messageRepository.countByRecipientIdAndReaded(userId, false);
    }

    public void addMessage(MessageFormDTO messageFormDTO) {
        Message message = new Message();
        message.setSender(userRepository.findOne(messageFormDTO.getSenderId()));
        message.setRecipient(userRepository.findOne(messageFormDTO.getRecipientId()));
        message.setTitle(messageFormDTO.getTitle());
        message.setText(messageFormDTO.getText());
        messageRepository.save(message);
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public Message getOne(Long id) {
        return messageRepository.findOne(id);
    }
}
