package by.home.springbootpetstoredb.service;


import by.home.springbootpetstoredb.entity.Token;
import by.home.springbootpetstoredb.entity.User;
import by.home.springbootpetstoredb.entity.UserRoleEnum;
import by.home.springbootpetstoredb.exception.AlreadyExistException;
import by.home.springbootpetstoredb.exception.NotFoundException;
import by.home.springbootpetstoredb.repository.TokenRepository;
import by.home.springbootpetstoredb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;


    public User save(User user){
        if (!userRepository.existsByUserName(user.getUserName())) {
            user.setRole(UserRoleEnum.USER);
            return userRepository.save(user);
        }
        throw new AlreadyExistException("This user already exists");
    }

    public Token auth(User user){
        User byUserName = userRepository.getByUserName(user.getUserName());
        if (user.equals(byUserName)) {
            long id = byUserName.getId();
            UserRoleEnum role = byUserName.getRole();
            Token token = tokenService.getToken(id, role);
            return token;
        }
        throw new NotFoundException("Incorrect login or password!");
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
