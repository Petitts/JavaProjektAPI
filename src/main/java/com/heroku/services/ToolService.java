package com.heroku.services;

import com.heroku.models.Tool;
import com.heroku.repositories.ToolRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ToolService {
    private final ToolRepository toolRepository;
    public List<Tool> getAllTools(){return toolRepository.findAll();}
    public Tool getTool(String type, String title){
        return toolRepository.findToolByTypeAndTitle(type, title);
    }
    public List<String> getTypes(){
        List<Tool> documents = toolRepository.findDistinctByA("a");
        List<String> types = new ArrayList<>();
        for(Tool document : documents){
            if(!types.contains(document.getType()))
                types.add(document.getType());
        }
        return types;
    }
    public List<String> getTitles(String type){
        List<Tool> documents = toolRepository.findDistinctByAAndType("a", type);
        List<String> titles = new ArrayList<>();
        for(Tool document : documents){
            if(!titles.contains(document.getTitle()))
                titles.add(document.getTitle());
        }
        return titles;
    }
}
