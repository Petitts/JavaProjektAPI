package com.heroku.services;

import com.heroku.models.Image;
import com.heroku.repositories.ImageRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;
    public List<Image> getAllImages(){
        return imageRepository.findAll();
    }
    public List<Image> getOneImage(String subject, String lecture, String number){
        return imageRepository.findImageBySubjectAndLectureAndNumber(subject, lecture, number);
    }
    public List<Image> getLectureImages(String subject, String lecture){
        return imageRepository.findImagesBySubjectAndLecture(subject, lecture);
    }
    public List<Image> getSubjectImages(String subject){
        return imageRepository.findImagesBySubject(subject);
    }
}
