package com.heroku.services;

import com.heroku.models.Question;
import com.heroku.repositories.QuestionRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service

public class QuestionService {
    private final QuestionRepository questionRepository;
    public List<Question> getRandomQuestions(Integer questionCount){
        List<Question> questionsAll = questionRepository.findAll();
        List<Question> questionResult = new ArrayList<>(Collections.emptyList());
        Random random = new Random();
        for(int i=0; i<questionCount; i++){
            int randomIndex = random.nextInt(questionsAll.size());
            questionResult.add(questionsAll.get(randomIndex));
        }
        return questionResult;
    }
    public  List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }
}
