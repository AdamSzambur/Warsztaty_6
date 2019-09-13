package pl.coderslab.warsztat6.twitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.warsztat6.twitter.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Long countByEmail(String email);
    User findByEmail(String email);
}
