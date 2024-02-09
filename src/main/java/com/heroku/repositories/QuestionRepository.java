package com.heroku.repositories;


import com.heroku.models.Question;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface QuestionRepository extends MongoRepository<Question, String> {
}
