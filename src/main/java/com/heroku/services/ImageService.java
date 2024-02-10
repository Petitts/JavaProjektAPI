package com.heroku.services;

import com.heroku.models.Image;
import com.heroku.repositories.ImageRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> addImage(String subject, String lecture, Integer number, String urlS){
        Integer maxNumber;
        if(subject == null || lecture == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subject and lecture is required");
        }
        if(number == null){
            List<Image> images = imageRepository.findImagesBySubjectAndLecture(subject, lecture);
            Optional<Integer> maxNumberO = images.stream().map(Image::getNumber).max(Comparator.naturalOrder());
            maxNumber = maxNumberO.orElse(0)+1;
            number = maxNumber;

        }
        if(!imageRepository.existsBySubjectAndLectureAndNumber(subject, lecture, number)){
            try{
                URL url = new URL(urlS);
                Image image = new Image(subject, lecture, number, url);
                return ResponseEntity.ok(imageRepository.save(image));
            }catch (MalformedURLException ex){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid URL");
            }

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image already exist");
    }
    public ResponseEntity<?> updateImage(String id, String newSubject, String newLecture, Integer newNumber, String newUrl){
        if(!imageRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found!");
        }
        Image updatedImage = imageRepository.findImageById(id);
        if(newSubject != null){
            updatedImage.setSubject(newSubject);
        }
        if(newLecture != null){
            updatedImage.setLecture(newLecture);
        }
        if(newNumber != null){
            List<Image> images = imageRepository.findImagesBySubjectAndLectureAndNumber(updatedImage.getSubject(), updatedImage.getLecture(), newNumber);
            if(images.isEmpty()){
                updatedImage.setNumber(newNumber);
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number already exist!");
            }
        }
        if(newUrl != null){
            try{
                URL url = new URL(newUrl);
                updatedImage.setUrl(url);
            }catch (MalformedURLException ex){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid URL");
            }
        }
        return ResponseEntity.ok(imageRepository.save(updatedImage));
    }
    public ResponseEntity<?> deleteImage(String id){
        if(!imageRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image does not exist");
        }
        Image image = imageRepository.findImageById(id);
        imageRepository.delete(image);
        return ResponseEntity.ok("Image "+ image.toString() +" successful deleted");
    }
}
