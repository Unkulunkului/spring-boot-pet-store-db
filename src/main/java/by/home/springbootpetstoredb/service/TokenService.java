package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.Token;
import by.home.springbootpetstoredb.entity.User;
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

    @Autowired
    private UserService userService;

    public Token getKey(long userId){
        Optional<Token> byUserId = tokenRepository.findByUserId(userId);
        if(byUserId.isPresent()){
            return byUserId.get();
        }
        UUID uuid = UUID.randomUUID();
        Token token = new Token(userId, uuid.toString());
        return tokenRepository.save(token);
    }

    public boolean isUser(String key){
        Optional<Token> byKey = tokenRepository.getByKey(key);
        return byKey.isPresent();
    }

    public boolean isAdmin(String key){
        Optional<Token> byKey = tokenRepository.getByKey(key);
        if (byKey.isPresent()) {
            User byId = userService.getById(byKey.get().getId());
            if (byId!=null && byId.getRole().equals(UserRoleEnum.ADMIN)) {
                return true;
            }
        }
        return false;
    }
}
