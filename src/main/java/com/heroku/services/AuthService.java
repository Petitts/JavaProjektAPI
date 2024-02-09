package com.heroku.services;

import com.heroku.models.Auth;
import com.heroku.repositories.AuthRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@Service
public class AuthService {
    private final AuthRepository authRepository;
    public boolean AuthUser(String username, String password){
        if(authRepository.findAuthByUser(username) != null){
            Auth correctAuth = authRepository.findAuthByUser(username);
            if(correctAuth.getPassword().equals(password)) return true;
            else return false;
        }
        else return false;

    }
    public ResponseEntity<?> AddAuth(Auth auth){
        try {
            if(authRepository.findAuthByUser(auth.getUser()) == null){
                String hashedPassword = createHash(auth.getPassword());
                auth.setPassword(hashedPassword);
                return ResponseEntity.ok(authRepository.save(auth));
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exist");
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No such algorithm");
        }
    }
    private String createHash(String toHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] plainBytes = toHash.getBytes();
        byte[] hashedBytes = md.digest(plainBytes);
        StringBuilder hashStringB = new StringBuilder();
        for(byte b : hashedBytes){
            hashStringB.append(String.format("%02x", b));
        }
        return hashStringB.toString();
    }
}
