package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.User;
import by.home.springbootpetstoredb.exception.AlreadyExistException;
import by.home.springbootpetstoredb.exception.NotFoundException;
import by.home.springbootpetstoredb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        if (!userRepository.existsByUserName(user.getUserName())) {
            return userRepository.save(user);
        }
        throw new AlreadyExistException("This user already exists");
    }

    @Transactional
    public void deleteByLogin(String login){
        if (userRepository.existsByUserName(login)) {
            userRepository.deleteByUserName(login);
        }else {
            throw new NotFoundException("User doesn't exist!");
        }
    }

    public User getByLogin(String login){
        User byUserName = userRepository.getByUserName(login);
        if (byUserName != null) {
            return byUserName;
        }
        throw new NotFoundException("User not found!");
    }

    public boolean updateByLogin(String login, User user){
        User byLogin = getByLogin(login);
        user.setId(byLogin.getId());
        userRepository.save(user);
        return true;
    }
}
