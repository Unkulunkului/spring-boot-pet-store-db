package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.User;
import by.home.springbootpetstoredb.entity.UserRoleEnum;
import by.home.springbootpetstoredb.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void createUser(){
        user = new User();
        user.setUserName("Olejka");
        user.setPassword("Olegunya");
        user.setRole(UserRoleEnum.ADMIN);
    }

    @Test
    void save() {
        user.setRole(UserRoleEnum.ADMIN);
        User actual = userRepository.save(user);
        assertEquals(user, actual);
    }

    @Test
    void deleteByLogin() {
        userRepository.save(user);
        userRepository.deleteByUserName("Olejka");
        assertNull(userRepository.getByUserName("Olejka"));
    }

    @Test
    void getByLogin() {
        userRepository.save(user);
        User actual = userRepository.getByUserName("Olejka");
        assertEquals(user, actual);
    }

    @Test
    void getById() {
        userRepository.save(user);
        Optional<User> byId = userRepository.findById(user.getId());
        assertEquals(user, byId.orElse(new User()));
    }
}