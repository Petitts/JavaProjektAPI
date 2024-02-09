package com.heroku.services;

import com.heroku.models.Code;
import com.heroku.repositories.CodeRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CodeService {
    private final CodeRepository codeRepository;
    public List<Code> getAllCodes(){
        List<Code> docuemnts = codeRepository.findAll();
        return docuemnts;
    }
    public List<Code> getLanguageCodes(String language) {
        List<Code> docuemnts = codeRepository.findCodesByLanguage(language);
        List<Code> result = new ArrayList<>();
        return docuemnts;
    }
    public List<Code> getLaboratoryCodes(String language, String laboratory)
    {
        List<Code> docuemnts = codeRepository.findCodesByLanguageAndLaboratory(language, laboratory);
        return docuemnts;
        }

    public List<Code> getExerciseCodes(String language, String laboratory, String exercise){
        return codeRepository.findCodesByLanguageAndLaboratoryAndExercise( language,  laboratory,  exercise);
    }
    public List<Code> getTitleCode(String title, String language, String laboratory, String exercise){
        return codeRepository.findCodeByLanguageAndLaboratoryAndExerciseAndTitle(language, laboratory, exercise, title);
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
    public ResponseEntity<?> addCode(String language, String laboratory, String exercise, String title, String content) {
        if(title == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title is required");
        }
        if(language == null){
            laboratory = null;
            exercise = null;
        } else if (laboratory == null) {
            exercise = null;
        }
        List<Code> codes = codeRepository.findCodeByLanguageAndLaboratoryAndExerciseAndTitle(language, laboratory, exercise, title);
        if(codes.isEmpty()){
            Code newCode = new Code(language, laboratory, exercise, title, content);
            codeRepository.save(newCode);
            return ResponseEntity.ok(newCode);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code already exist");
        }
    }
    public ResponseEntity<?> updateCode(String id, String language, String laboratory, String exercise, String title, String content){
        List<Code> codes = codeRepository.findCodeById(id);
        if(codes.size() == 1){
            Code updatedCode = codes.get(0);
            if(language != null){
                updatedCode.setLanguage(language);
            }
            if(laboratory != null && updatedCode.getLanguage() != null){
                updatedCode.setLaboratory(laboratory);
            }
            if(exercise != null && updatedCode.getLanguage() != null && updatedCode.getLaboratory() != null){
                updatedCode.setExercise(exercise);
            }
            if(title != null){
                updatedCode.setTitle(title);
            }
            if(content != null){
                updatedCode.setContent(content);
            }
            codeRepository.save(updatedCode);
            return ResponseEntity.ok(updatedCode);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Code not found");
        }
    }
    public ResponseEntity<?> deleteCode(String id){
        List<Code> codes = codeRepository.findCodeById(id);
        if(codes.size() == 1){
            Code deletedCode = codes.get(0);
            codeRepository.delete(deletedCode);
            return ResponseEntity.ok("Code deleted successful");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Code not found");
        }

    }
}

