package com.heroku.controllers;


import com.heroku.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping
    public boolean authUser(
            @RequestParam String username,
            @RequestParam String password
    ){
        return authService.authUser(username, password);
    }

}
