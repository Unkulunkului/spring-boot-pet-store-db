package by.home.springbootpetstoredb.service;


import by.home.springbootpetstoredb.entity.Token;
import by.home.springbootpetstoredb.entity.User;
import by.home.springbootpetstoredb.entity.UserRoleEnum;
import by.home.springbootpetstoredb.exception.AlreadyExistException;
import by.home.springbootpetstoredb.exception.NotFoundException;
import by.home.springbootpetstoredb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;


    public User save(User user){
        if (!userRepository.existsByUserName(user.getUserName())) {
            user.setRole(UserRoleEnum.USER);
            log.info(user+" was saved");
            return userRepository.save(user);
        }
        log.warn("Try to save already existent user");
        throw new AlreadyExistException("This user already exists");
    }


    public Token auth(User user){
        User byUserName = userRepository.getByUserName(user.getUserName());
        if (user.equals(byUserName)) {
            long id = byUserName.getId();
            Token token = tokenService.getKey(id);
            log.info(user+" got token "+token);
            return token;
        }
        log.warn("Try to auth with wrong data");
        throw new NotFoundException("Incorrect login or password!");
    }

    @Transactional
    public void deleteByLogin(String login){
        if (userRepository.existsByUserName(login)) {
            userRepository.deleteByUserName(login);
            log.info("User "+login+" was deleted");
        }else {
            log.warn("Try to delete non-existent user (login: "+login+")");
            throw new NotFoundException("User doesn't exist!");
        }
    }

    public User getByLogin(String login){
        User byUserName = userRepository.getByUserName(login);
        if (byUserName != null) {
            log.info(byUserName+" user by login");
            return byUserName;
        }
        log.warn("Try to get non-existent user (login: "+login+")");
        throw new NotFoundException("User not found!");
    }

    public boolean updateByLogin(String login, User user){
        User byLogin = getByLogin(login);
        user.setId(byLogin.getId());
        userRepository.save(user);
        log.info(user+" was update");
        return true;
    }

    public User getById(long id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            log.info(byId.get()+" user by id");
            return byId.get();
        }
        log.warn("Try to get non-existent user (id: "+id+")");
        return null;
    }
}
