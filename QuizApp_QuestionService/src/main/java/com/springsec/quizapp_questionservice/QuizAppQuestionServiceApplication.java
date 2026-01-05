package com.springsec.quizapp_questionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizAppQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizAppQuestionServiceApplication.class, args);
    }

}
