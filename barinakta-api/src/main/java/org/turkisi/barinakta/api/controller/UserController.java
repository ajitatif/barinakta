package org.turkisi.barinakta.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.turkisi.barinakta.api.crypto.HashHelper;
import org.turkisi.barinakta.api.error.AuthenticationException;
import org.turkisi.barinakta.api.error.EntityNotFoundException;
import org.turkisi.barinakta.api.model.User;
import org.turkisi.barinakta.api.model.request.LoginRequestModel;
import org.turkisi.barinakta.api.repo.UserRepository;

import java.net.URI;
import java.security.NoSuchAlgorithmException;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Controller
@RequestMapping(path = "/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

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

    @RequestMapping(path = "/login", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    User login(@RequestBody LoginRequestModel loginRequest) throws AuthenticationException {

        User user = userRepository.findByUserName(loginRequest.getUsername());
        if (user == null || !isPasswordMatch(loginRequest.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid username or password");
        }

        return user;
    }

    private boolean isPasswordMatch(String clearPassword, String hashedPassword) {

        try {
            return HashHelper.calculateHash(clearPassword).equals(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Cannot calculate MD5", e);
            return false;
        }
    }
}
