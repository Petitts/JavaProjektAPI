package com.heroku.services;

import com.heroku.models.Image;
import com.heroku.models.Question;
import com.heroku.models.Tool;
import com.heroku.repositories.ToolRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
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
    public ResponseEntity<?> addTool(Tool newTool){
        if(newTool.getType() == null || newTool.getTitle() == null || newTool.getUrl() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type, title and URL are required!");
        }
        if(!toolRepository.existsByTypeAndTitle(newTool.getType(), newTool.getTitle())){
            try{
                URL url = new URL(newTool.getUrl());
                newTool.setUrl(url.toString());
                return ResponseEntity.ok(toolRepository.save(newTool));
            }catch (MalformedURLException ex){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid URL");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tool already exist");

    }
    public ResponseEntity<?> updateTool(Tool tool){
        if(!toolRepository.existsById(tool.getId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tool not found!");
        }
        Tool updatedTool = toolRepository.findToolById(tool.getId());
        if(tool.getType() != null){
            updatedTool.setType(tool.getType());
        }
        if(tool.getTitle() != null){
            updatedTool.setTitle(tool.getTitle());
        }
        if(tool.getUrl() != null){
            updatedTool.setUrl(tool.getUrl());
        }

        if(updatedTool.getType() == null || updatedTool.getTitle() == null || updatedTool.getUrl() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type, title and URL are required!");
        }
        try{
            URL url = new URL(updatedTool.getUrl());
            updatedTool.setUrl(url.toString());
            return ResponseEntity.ok(toolRepository.save(updatedTool));
        }catch (MalformedURLException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid URL");
        }
    }
    public ResponseEntity<?> deleteTool(String id){
        if(!toolRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tool does not exist");
        }
        Tool tool = toolRepository.findToolById(id);
        toolRepository.delete(tool);
        return ResponseEntity.ok("Image "+ tool.toString() +" successful deleted");

    }
}
