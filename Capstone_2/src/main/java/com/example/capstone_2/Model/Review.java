package com.example.capstone_2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Project ID cannot be null ")
    @Column(columnDefinition = "int not null")
    private Integer projectId;

    @NotNull(message = "Rate cannot be empty")
    @Max(value = 5 , message = "Rate must be out of five")
    @Column(columnDefinition = "int not null")
    private Integer rate;

    @NotEmpty(message = "Content cannot be empty")
    @Column(columnDefinition = "varchar(150) not null")
    private String content;

    @CreationTimestamp
    @Column(columnDefinition = "dateTime ")
    private LocalDate createdAt;
}
