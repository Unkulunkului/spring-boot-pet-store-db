package by.home.springbootpetstoredb.resource;

import by.home.springbootpetstoredb.entity.Token;
import by.home.springbootpetstoredb.entity.User;
import by.home.springbootpetstoredb.entity.UserDTO;
import by.home.springbootpetstoredb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@RestController
@RequestMapping(path = "user")
@Slf4j
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        User save = userService.save(user);
        log.info(user+" was saved");
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<String> auth(@Valid @RequestBody UserDTO userDTO){
        User user = new User();
        String userName = userDTO.getUserName();
        String password = userDTO.getPassword();
        user.setUserName(userName);
        user.setPassword(password);
        Token token = userService.auth(user);
        log.info(userDTO.getUserName()+" got token "+token.getKey());
        return new ResponseEntity<>(token.getKey(), HttpStatus.OK);
    }

    @GetMapping(path = "/{login}")
    public ResponseEntity<User> getByLogin(@PathVariable("login") String login){
        User byLogin = userService.getByLogin(login);
        log.info("User "+byLogin+" was returned by login: " +login);
        return new ResponseEntity<>(byLogin,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{login}")
    public ResponseEntity<String> deleteByLogin(@PathVariable("login") String login){
        userService.deleteByLogin(login);
        log.info("User was deleted by login: " +login);
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }

    @PutMapping(path = "{login}")
    public ResponseEntity<User> updateByLogin(@PathVariable("login")String login, @Valid @RequestBody User user){
        userService.updateByLogin(login, user);
        log.info("User was updated by login" +login);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
