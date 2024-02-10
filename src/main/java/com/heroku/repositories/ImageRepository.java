package com.heroku.repositories;


import com.heroku.models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ImageRepository extends MongoRepository<Image, String> {
    List<Image> findImageBySubjectAndLectureAndNumber(String subject, String lecture, String number);
    List<Image> findImagesBySubjectAndLecture(String subject, String lecture);
    List<Image> findImagesBySubject(String subject);
    Boolean existsBySubjectAndLectureAndNumber(String subject, String lecture, Integer number);
    boolean existsById(String id);
    Image findImageById(String id);
    List<Image> findImagesBySubjectAndLectureAndNumber(String subject, String lecture, Integer number);
}
