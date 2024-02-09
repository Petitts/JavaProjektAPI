package com.heroku.controllers;



import com.heroku.models.Question;
import com.heroku.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping
    public List<Question> fetchAllQuestions(
            @RequestParam(name = "questionCount", required = false) Integer questionCount){
        if(questionCount != null ){
            if(questionCount>20 || questionCount < 1)
                questionCount = 20;
            return questionService.getRandomQuestions(questionCount);
        }
        return questionService.getAllQuestions();
    }

}
