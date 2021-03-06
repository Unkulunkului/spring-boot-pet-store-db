package by.home.springbootpetstoredb.repository;

import by.home.springbootpetstoredb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUserName(String login);
    boolean existsByUserName(String login);
    void deleteByUserName(String login);
    boolean existsById(long id);
}
