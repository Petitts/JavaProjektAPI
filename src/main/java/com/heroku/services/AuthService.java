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
    public boolean authUser(String username, String password){
        if(authRepository.findAuthByUser(username) != null){
            Auth correctAuth = authRepository.findAuthByUser(username);
            if(correctAuth.getPassword().equals(password)) return true;
            else return false;
        }
        else return false;

    }
    public ResponseEntity<?> addAuth(Auth auth){
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
    public ResponseEntity<?> updateAuth(String oldUsername, String newUsername, String newPassword){
        if(!authRepository.existsByUser(oldUsername)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+oldUsername+" does not exist");
        }
        Auth updatedAuth = authRepository.findAuthByUser(oldUsername);
        if(newUsername != null){
            updatedAuth.setUser(newUsername);
        }
        if(newPassword != null){
            try {
                String hashedNewPassword = createHash(newPassword);
                updatedAuth.setPassword(hashedNewPassword);
            }catch (NoSuchAlgorithmException ex){
                ex.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No such algorithm");
            }
        }
        authRepository.save(updatedAuth);
        return ResponseEntity.ok(updatedAuth);
    }
    public ResponseEntity<?> deleteAuth(String username){
        if(!authRepository.existsByUser(username)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+username+" does not exist");
        }
        Auth auth = authRepository.findAuthByUser(username);
        authRepository.delete(auth);
        return ResponseEntity.ok("User "+auth.getUser()+" succesful deleted");
    }
    public ResponseEntity<?> getAuth(String username){
        if(!authRepository.existsByUser(username)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User "+username+" does not exist");
        }
        return ResponseEntity.ok(authRepository.findAuthByUser(username));
    }
    public ResponseEntity<?> getAllAuths(){
        return ResponseEntity.ok(authRepository.findAll());
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
