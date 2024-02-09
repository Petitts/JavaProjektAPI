package com.heroku.services;

import com.heroku.models.Code;
import com.heroku.repositories.CodeRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CodeService {
    private final CodeRepository codeRepository;
    public List<Code> getAllCodes(){
        List<Code> docuemnts = codeRepository.findAll();
        List<Code> result = new ArrayList<>();
        for(Code document : docuemnts){
            if(document.getLanguage() == null){
                result.add(document);
            }
        }
        return result;
    }
    public List<Code> getLanguageCodes(String language) {
        List<Code> docuemnts = codeRepository.findCodesByLanguage(language);
        List<Code> result = new ArrayList<>();
        for (Code document : docuemnts) {
            if (document.getLaboratory() == null) {
                result.add(document);
            }
        }
        return result;
    }
    public List<Code> getLaboratoryCodes(String language, String laboratory)
    {
        List<Code> docuemnts = codeRepository.findCodesByLanguageAndLaboratory(language, laboratory);
        List<Code> result = new ArrayList<>();
        for (Code document : docuemnts) {
            if (document.getExercise() == null) {
                result.add(document);
            }
        }
        return result;
        }

    public List<Code> getExerciseCodes(String language, String laboratory, String exercise){
        return codeRepository.findCodesByLanguageAndLaboratoryAndExercise( language,  laboratory,  exercise);
    }
    public List<Code> getTitleCode(String title, String language, String laboratory, String exercise){
        if(title == null){
            return null;
        }
        if(exercise != null && laboratory != null && language != null){
            return codeRepository.findCodeByLanguageAndLaboratoryAndExerciseAndTitle(language, laboratory, exercise, title);
        }
        else if(exercise == null && laboratory != null && language != null){
            return codeRepository.findCodeByLanguageAndLaboratoryAndTitle(language, laboratory, title);
        }else if(exercise == null && laboratory == null && language != null) {
            return codeRepository.findCodeByLanguageAndTitle(language, title);
        }
        return codeRepository.findCodeByType(title);
    }
    public List<String> getLanguages(){
        List<Code> documents = codeRepository.findDistinctByType("code");
        List<String> languages = new ArrayList<>();
        for(Code document : documents){
            if(!languages.contains(document.getLanguage()))
                languages.add(document.getLanguage());
        }
        return languages;
    }
    public List<String> getLaboratories(String language){
        List<Code> documents = codeRepository.findDistinctByTypeAndLanguage("code", language);
        List<String> laboratories = new ArrayList<>();
        for(Code document : documents){
            if(!laboratories.contains(document.getLaboratory()))
                laboratories.add(document.getLaboratory());
        }
        return laboratories;
    }
    public List<String> getExercises(String language, String laboratory){
        List<Code> documents = codeRepository.findDistinctByTypeAndLanguageAndLaboratory("code", language, laboratory);
        List<String> exercises = new ArrayList<>();
        for(Code document : documents){
            if(!exercises.contains(document.getExercise()))
                exercises.add(document.getExercise());
        }
        return exercises;
    }
    public List<String> getTitles(String language, String laboratory, String exercise){
        List<Code> documents = codeRepository.findDistinctByTypeAndLanguageAndLaboratoryAndExercise("code", language, laboratory, exercise);
        List<String> titles = new ArrayList<>();
        for(Code document : documents){
            if(!titles.contains(document.getTitle()))
                titles.add(document.getTitle());
        }
        return titles;
    }
}
