package com.heroku.controllers;

import com.heroku.models.Tool;
import com.heroku.services.ToolService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
