package com.heroku.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;

@Data
@Document
public class Image {
    @Id
    private String id;
    private String subject;
    private String lecture;
    private Integer number;
    private URL url;

    public Image(String subject, String lecture, Integer number, URL url) {
        this.subject = subject;
        this.lecture = lecture;
        this.number = number;
        this.url = url;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", lecture='" + lecture + '\'' +
                ", number=" + number +
                ", url=" + url +
                '}';
    }
}
