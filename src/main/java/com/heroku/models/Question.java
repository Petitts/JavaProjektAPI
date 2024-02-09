package com.heroku.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Question {
    @Id
    private String id;
    private String questionText;
    private List<String> correctAnswers;
    private List<String> options;
    private String imageURL;

    public Question(String questionText, List<String> correctAnswers, List<String> options, String imageURL) {
        this.questionText = questionText;
        this.correctAnswers = correctAnswers;
        this.options = options;
        this.imageURL = imageURL;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", questionText='" + questionText + '\'' +
                ", correctAnswers=" + correctAnswers +
                ", options=" + options +
                '}';
    }
}
