package by.home.springbootpetstoredb.resource;

import by.home.springbootpetstoredb.entity.User;
import by.home.springbootpetstoredb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping(path = "user")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody User user){
        User save = userService.save(user);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @GetMapping(path = "/{login}")
    public ResponseEntity<User> getByLogin(@PathVariable("login") String login){
        User byLogin = userService.getByLogin(login);
        return new ResponseEntity<>(byLogin,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{login}")
    public ResponseEntity<String> deleteByLogin(@PathVariable("login") String login){
        userService.deleteByLogin(login);
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }

    @PutMapping(path = "{login}")
    public ResponseEntity<User> updateByLogin(@PathVariable("login")String login, @Valid @RequestBody User user){
        userService.updateByLogin(login, user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
