package dev.huai.controllers;

import dev.huai.models.User;
import dev.huai.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

        /*
        GET     /user - get all dog records         localhost:8080/dogs
                optional request param: breed       localhost:8080/dogs?breed=pitbull
                .... etc
        GET     /user/{id} - get user information by id
        POST    /user - validate user credential, return boolean
        PUT     /user/{id} - update an existing user password
     */

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseBody
    public User getUserByCredential(@RequestBody User user){
        return userService.getUserByCredential(user.getUserId(), user.getPassword());
    }

}
