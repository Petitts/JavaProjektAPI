package com.heroku.services;

import com.heroku.models.Auth;
import com.heroku.repositories.AuthRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final AuthRepository authRepository;
    public boolean authUser(String username, String password){
        Auth correctAuth = authRepository.findAuthByUser(username);
        return correctAuth.getUser().equals(username) && correctAuth.getPassword().equals(password);
    }
}
