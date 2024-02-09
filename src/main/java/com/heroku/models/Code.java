package com.heroku.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Code {
    @Id
    private String id;
    private String type = "code";
    private String language;
    private String laboratory;
    private String exercise;
    private String title;
    private String content;

    public Code(String language, String laboratory, String exercise, String title, String content) {
        this.language = language;
        this.laboratory = laboratory;
        this.exercise = exercise;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id='" + id + '\'' +
                ", language='" + language + '\'' +
                ", laboratory='" + laboratory + '\'' +
                ", exercise='" + exercise + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
