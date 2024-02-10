package com.heroku.controllers;

import com.heroku.models.Tool;
import com.heroku.services.ToolService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/tool")
public class ToolController {
    private final ToolService toolService;
    @GetMapping
    public Tool fetchTool(
            @RequestParam(name = "type") String type,
            @RequestParam(name = "title") String title
    ){
        return toolService.getTool(type, title);
    }

    @GetMapping("types")
    public List<String> fetchTypes(){return toolService.getTypes();}
    @GetMapping("titles")
    public List<String> fetchTitles(
            @RequestParam(name = "type") String type
    ){
        return toolService.getTitles(type);
    }
    @PostMapping
    public ResponseEntity<?> addTool(Tool tool){
        return toolService.addTool(tool);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateTool(Tool tool){
        return toolService.updateTool(tool);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTool(@PathVariable String id){
        return toolService.deleteTool(id);
    }
}
