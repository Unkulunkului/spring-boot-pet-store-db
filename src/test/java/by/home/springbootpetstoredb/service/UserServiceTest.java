package by.home.springbootpetstoredb.service;

import by.home.springbootpetstoredb.entity.User;
import by.home.springbootpetstoredb.entity.UserRoleEnum;
import by.home.springbootpetstoredb.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        User expected = new User();
        expected.setUserName("Olejka");
        expected.setPassword("Olegunya");
        expected.setRole(UserRoleEnum.ADMIN);
        User actual = userRepository.save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void deleteByLogin() {
    }

    @Test
    void getByLogin() {
        User expected = new User();
        expected.setUserName("Olejka");
        expected.setPassword("Olegunya");
        expected.setRole(UserRoleEnum.ADMIN);
        userRepository.save(expected);
        User actual = userRepository.getByUserName("Olejka");
        assertEquals(expected, actual);
    }

    @Test
    void getById() {
        User expected = new User();
        expected.setUserName("Olejka");
        expected.setPassword("Olegunya");
        expected.setRole(UserRoleEnum.ADMIN);
        userRepository.save(expected);
        User actual = userRepository.findById(1L).get();
        assertEquals(expected, actual);
    }
}