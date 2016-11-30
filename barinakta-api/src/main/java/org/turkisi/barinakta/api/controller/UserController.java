package org.turkisi.barinakta.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.turkisi.barinakta.api.model.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Controller
@RequestMapping(path = "/user")
public class UserController {

    @RequestMapping(path = "", method = RequestMethod.GET)
    public
    @ResponseBody Collection<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        User user = new User();
        user.setFirstName("Gökalp");
        user.setLastName("Gürbüzer");
        user.setUserName("gokalpg");
        user.setPassword("gokalp");
        users.add(user);

        return users;
    }
}
