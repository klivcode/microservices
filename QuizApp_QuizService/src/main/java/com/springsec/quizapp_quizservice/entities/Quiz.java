package com.springsec.quizapp_quizservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> questionIds;

}
