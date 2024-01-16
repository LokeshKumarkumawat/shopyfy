package com.ezorx.ecoom.controller;

import com.ezorx.ecoom.entity.JwtRequest;
import com.ezorx.ecoom.entity.JwtResponse;
import com.ezorx.ecoom.service.JwtService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        System.out.println("JWWWWWWT"+jwtRequest);

        return jwtService.createJwtToken(jwtRequest);

    }
}
