package com.heroku.controllers;



import com.heroku.models.Image;
import com.heroku.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/images")
public class ImageController {
    private final ImageService imageService;
    @GetMapping
    public List<Image> fetchImages(
            @RequestParam(name = "subject", required = false) String subject,
            @RequestParam(name = "lecture", required = false) String lecture,
            @RequestParam(name = "number", required = false) String number){
        if(subject == null && lecture == null && number == null){
            return imageService.getAllImages();
        } else if (number == null && subject != null && lecture != null) {
            return imageService.getLectureImages(subject, lecture);
        } else if (lecture == null && number == null && subject != null) {
            return imageService.getSubjectImages(subject);
        } else{
            return imageService.getOneImage(subject, lecture, number);
        }
    }
    @PostMapping
    public ResponseEntity<?> addImage(String subject, String lecture, Integer number, String url){
        return imageService.addImage(subject, lecture, number, url);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateImage(@PathVariable String id, String subject, String lecture, Integer number, String url){
        return imageService.updateImage(id, subject, lecture, number, url);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteImage(@PathVariable String id){
        return imageService.deleteImage(id);
    }
}
