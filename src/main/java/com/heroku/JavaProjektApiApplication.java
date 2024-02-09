package com.heroku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JavaProjektApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaProjektApiApplication.class, args);
    }
    @GetMapping
    public String hello(){
        return "Hello World!!!";
    }

}
