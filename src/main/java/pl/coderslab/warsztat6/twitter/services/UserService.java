package pl.coderslab.warsztat6.twitter.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.warsztat6.twitter.dto.UserEditFormDTO;
import pl.coderslab.warsztat6.twitter.model.User;
import pl.coderslab.warsztat6.twitter.repositories.UserRepository;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateUser(UserEditFormDTO data) {
        User user = userRepository.findByEmail(data.getEmail());

        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userRepository.save(user);
    }

    public User getUserById(Long userId, Boolean allData) {
        User user = userRepository.findOne(userId);
        if (allData) {
            user.getInbox().size();
            user.getOutbox().size();
        }
        return user;
    }
}
