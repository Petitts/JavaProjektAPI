package com.heroku.repositories;


import com.heroku.models.Code;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CodeRepository extends MongoRepository<Code, String> {
    List<Code> findCodesByLanguage(String language);
    List<Code> findCodesByLanguageAndLaboratory(String language, String laboratory);
    List<Code> findCodesByLanguageAndLaboratoryAndExercise(String language, String laboratory, String exercise);
    List<Code> findCodeByLanguageAndLaboratoryAndExerciseAndTitle(String language, String laboratory, String exercise, String title);
    List<Code> findCodeByType(String type);
    List<Code> findCodeByLanguageAndTitle(String language, String title);
    List<Code> findCodeByLanguageAndLaboratoryAndTitle(String language, String laboratory, String title);
    List<Code> findDistinctByType(String type); //get language
    List<Code> findDistinctByTypeAndLanguage(String type, String language); //get laboratory
    List<Code> findDistinctByTypeAndLanguageAndLaboratory(String type, String language, String laboratory); //get exercise
    List<Code> findDistinctByTypeAndLanguageAndLaboratoryAndExercise(String type, String language, String laboratory, String exercise); // get codes
}
