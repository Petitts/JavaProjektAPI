package com.heroku.services;

import com.heroku.models.Image;
import com.heroku.models.Question;
import com.heroku.repositories.QuestionRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
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

    public ResponseEntity<?> addQuestion(Question question){
        if(question.getQuestionText() == null || question.getQuestionText().isEmpty() || question.getCorrectAnswers() == null || question.getOptions() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question text, correct answer and options are required!");
        }
        if(question.getOptions().size() != 4){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Options must have 4 elements!");
        }
        if(question.getCorrectAnswers().size() > 4){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only 4 answer can be correct!");
        }
        for(Integer answer : question.getCorrectAnswers()){
            if(answer > 3 || answer < 0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Correct answer index out of scope");
            }
        }
        if(question.getImageURL() != null){
            try{
                URL url = new URL(question.getImageURL());
                question.setImageURL(url.toString());
            }catch (MalformedURLException ex){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid URL");
            }
        }

        return  ResponseEntity.ok(questionRepository.save(question));
    }

     public ResponseEntity<?> updateQuestion(String id, Question question){
         if(!questionRepository.existsById(id)){
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found!");
         }
         Question updatedQuestion = questionRepository.findQuestionById(id);
         if(question.getQuestionText() != null){
             updatedQuestion.setQuestionText(question.getQuestionText());
         }
         if(question.getCorrectAnswers() != null){
             updatedQuestion.setCorrectAnswers(question.getCorrectAnswers());
         }
         if(question.getOptions() != null){
             updatedQuestion.setOptions(question.getOptions());
         }
         if(question.getImageURL() != null){
             updatedQuestion.setImageURL(question.getImageURL());
         }

         if(updatedQuestion.getQuestionText() == null || updatedQuestion.getQuestionText().isEmpty() || updatedQuestion.getCorrectAnswers() == null || updatedQuestion.getOptions() == null){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question text, correct answer and options are required!");
         }
         if(updatedQuestion.getOptions().size() != 4){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Options must have 4 elements!");
         }
         if(updatedQuestion.getCorrectAnswers().size() > 4){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only 4 answer can be correct!");
         }
         for(Integer answer : updatedQuestion.getCorrectAnswers()){
             if(answer > 3 || answer < 0){
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Correct answer index out of scope");
             }
         }
         if(updatedQuestion.getImageURL() != null){
             try{
                 URL url = new URL(updatedQuestion.getImageURL());
                 updatedQuestion.setImageURL(url.toString());
             }catch (MalformedURLException ex){
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid URL");
             }
         }

         return  ResponseEntity.ok(questionRepository.save(updatedQuestion));
     }
    public ResponseEntity<?> deleteQuestion(String id){
        if(!questionRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question does not exist");
        }
        Question question = questionRepository.findQuestionById(id);
        questionRepository.delete(question);
        return ResponseEntity.ok("Image "+ question.toString() +" successful deleted");
    }
}
