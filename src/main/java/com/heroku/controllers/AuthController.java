package com.heroku.controllers;


import com.heroku.models.Auth;
import com.heroku.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("add")
    public ResponseEntity<?> createAuth(
            @RequestParam String username,
            @RequestParam String password
    ){
        Auth auth = new Auth(username, password);
        return authService.addAuth(auth);
    }
    @PutMapping("{oldUsername}")
    public ResponseEntity<?> updateAuth(@PathVariable String oldUsername,
                                        @RequestParam(required = false) String username,
                                        @RequestParam(required = false) String password){
        return authService.updateAuth(oldUsername, username, password);
    }
}
