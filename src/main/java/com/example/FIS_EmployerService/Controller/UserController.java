package com.example.FIS_EmployerService.Controller;

import com.example.FIS_EmployerService.Service.UserService;
import com.example.FIS_EmployerService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user)
    {

        return userService.registerUser(user);
    }

}
