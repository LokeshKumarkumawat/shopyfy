package com.ezorx.ecoom.controller;

import com.ezorx.ecoom.entity.User;
import com.ezorx.ecoom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class RoleController {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUser(){
        userService.initRolesAndUser();
    }



    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "THis URL IS ONLY ACCESSABLE TO Admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "THIS URL IS ONLY ACCESSABLE TO USER";
    }
}
