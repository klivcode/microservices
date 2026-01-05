package com.springsec.quizapp_quizservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizAppQuizServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizAppQuizServiceApplication.class, args);
    }

}
