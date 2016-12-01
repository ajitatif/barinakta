package org.turkisi.barinakta.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.turkisi.barinakta.api.error.EntityNotFoundException;
import org.turkisi.barinakta.api.model.User;
import org.turkisi.barinakta.api.repo.UserRepository;

import java.net.URI;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public
    @ResponseBody
    Iterable<User> getAllUsers() {

        return userRepository.findAll();
    }

    @RequestMapping(path = "", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {

        User savedUser = userRepository.save(user);

        return ResponseEntity.created(URI.create("/user/" + savedUser.getId())).build();
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT, consumes = "application/json")
    public @ResponseBody void saveUser(@PathVariable(name = "userId") Long userId, @RequestBody User user) throws EntityNotFoundException {

        User userInDb = userRepository.findOne(userId);
        if (userInDb == null) {
            throw new EntityNotFoundException("Invalid user id: " + userId);
        }
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userInDb.setLastName(user.getLastName());
        userInDb.setFirstName(user.getFirstName());
        userRepository.save(userInDb);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUser(@PathVariable(name = "userId") Long userId) {
        return userRepository.findOne(userId);
    }
}
