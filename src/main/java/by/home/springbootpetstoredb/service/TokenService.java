package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Token;
import by.home.springbootpetstoredb.entity.UserRoleEnum;
import by.home.springbootpetstoredb.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token getToken(long userId, UserRoleEnum role){
        Optional<Token> byUserId = tokenRepository.findByUserId(userId);
        if(byUserId.isPresent()){
            return byUserId.get();
        }
        UUID uuid = UUID.randomUUID();
        Token token = new Token(userId, role, uuid.toString());
        return tokenRepository.save(token);
    }

    public Token valid(String key){
        Optional<Token> byKey = tokenRepository.getByKey(key);
        if (byKey.isPresent()) {
            return byKey.get();
        }
        return null;
    }
}
