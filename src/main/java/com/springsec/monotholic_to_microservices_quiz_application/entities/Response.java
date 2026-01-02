package com.springsec.monotholic_to_microservices_quiz_application.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Integer id;
    private String response;
}
