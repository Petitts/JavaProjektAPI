package com.heroku.controllers;



import com.heroku.models.Question;
import com.heroku.services.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<?> addQuestion(Question question){
        return questionService.addQuestion(question);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable String id, Question question){
        return questionService.updateQuestion(id, question);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable String id){
        return questionService.deleteQuestion(id);
    }
}
