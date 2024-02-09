package com.heroku.controllers;


import com.heroku.models.Code;
import com.heroku.services.CodeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/code")
public class CodeController {
    private final CodeService codeService;

    @GetMapping
    public List<Code> fetchCode(
            @RequestParam(name = "language", required = false) String language,
            @RequestParam(name = "laboratory", required = false) String laboratory,
            @RequestParam(name = "exercise", required = false) String exercise,
            @RequestParam(name = "title", required = false) String title
    ){
        if(language == null && laboratory == null && exercise == null && title == null){
            return codeService.getAllCodes();
        }
        else if(language != null && laboratory == null && exercise == null && title == null){
            return codeService.getLanguageCodes( language);
        }else if(language != null && laboratory != null && exercise == null && title == null){
            return codeService.getLaboratoryCodes( language,  laboratory);
        }else if(language != null && laboratory != null && exercise != null && title == null){
            return codeService.getExerciseCodes( language,  laboratory,  exercise);
        }else if(language != null && laboratory != null && exercise != null && title != null){
            return codeService.getTitleCode(title, language,  laboratory,  exercise);
        }else if(language != null && laboratory != null && exercise == null && title != null){
            return codeService.getTitleCode(title, language, laboratory, null);
        }else if(language != null && laboratory == null && exercise == null && title != null){
            return codeService.getTitleCode(title, language, null, null);
        }
        else if(language == null && laboratory == null && exercise == null && title != null){
            return codeService.getTitleCode(title, null, null, null);
        }
        return null;
    }
    @GetMapping("language")
    public List<String> fetchLanguages(){
        return codeService.getLanguages();
    }
    @GetMapping("laboratory")
    public List<String> fetchLaboratories(
            @RequestParam(name = "language", required = false) String language
    ){
        if(language != null){
            return codeService.getLaboratories(language);
        }
        return null;
    }
    @GetMapping("exercise")
    public List<String> fetchExercises(
            @RequestParam(name = "language", required = false) String language,
            @RequestParam(name = "laboratory", required = false) String laboratory
    ){
        if(language != null && laboratory != null){
            return codeService.getExercises(language, laboratory);
        }
        return null;
    }
    @GetMapping("title")
    public List<String> fetchTitles(
        @RequestParam(name = "language", required = false) String language,
        @RequestParam(name = "laboratory", required = false) String laboratory,
        @RequestParam(name = "exercise", required = false) String exercise
    ){
        if(language != null && laboratory != null && exercise != null){
            return codeService.getTitles(language, laboratory, exercise);
        }
        return null;
    }
}
