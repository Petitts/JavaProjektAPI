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


    /*
    @Bean
    CommandLineRunner runner(QuestionRepository questionRepository, ImageRepository imageRepository){
        return args->{
            //Integer[] correct={0, 3};
            //String[] options={"1", "2", "3", "4", "5"};
            Question question = new Question("Dokad noca tupta jez",
                    List.of(1,3),
                    List.of("1", "2", "3", "4"));
            questionRepository.insert(question);
            Image image = new Image("ICK", "design_4_all",0, new URL("https://imgur.com/"));
            imageRepository.insert(image);
        };
    }
    */
}
