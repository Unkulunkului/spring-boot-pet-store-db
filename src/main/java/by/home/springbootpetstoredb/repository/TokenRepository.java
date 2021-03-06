package by.home.springbootpetstoredb.repository;


import by.home.springbootpetstoredb.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByUserId(Long id);
    Optional<Token> getByKey(String key);
}
