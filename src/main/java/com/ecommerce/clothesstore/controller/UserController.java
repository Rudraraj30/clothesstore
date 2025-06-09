package com.ecommerce.clothesstore.controller;


import com.ecommerce.clothesstore.entity.AppUser;
import com.ecommerce.clothesstore.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public void saveUser(@RequestBody AppUser user)
    {
        System.out.println("here enters a user");
        System.out.println(user);

        userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AppUser user)
    {
        System.out.println(user);
        return userService.verify(user);

    }
    @GetMapping("/greet")
    public String greet()
    {
        return "hello world";
    }

}
