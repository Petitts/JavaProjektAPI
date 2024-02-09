package com.heroku.repositories;


import com.heroku.models.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AuthRepository extends MongoRepository<Auth, String> {
    Auth findAuthByUser(String user);
    Boolean existsByUser(String user);
}
