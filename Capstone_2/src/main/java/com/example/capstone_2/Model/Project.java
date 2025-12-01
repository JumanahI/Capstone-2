package com.example.capstone_2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Student ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer studentId;

    @NotEmpty(message = "Title cannot be null")
    @Column(columnDefinition = "varchar(120) not null")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Column(columnDefinition = "varchar(250) not null")
    private String description;

    @NotEmpty(message = "Filed cannot be empty")
    @Column(columnDefinition = "varchar(130) not null")
    private String filed;

    @NotEmpty(message = "GitHub URL cannot be empty")
    @Column(columnDefinition = "varchar(140) not null")
    private String gitHubUrl;

    @NotEmpty(message = "Documentation URL cannot be empty")
    @Column(columnDefinition = "varchar(140) not null")
    private String documentationUrl;
}
