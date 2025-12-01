package com.example.capstone_2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Visitor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Full name cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String fullName;

    @NotEmpty(message = "Username cannot be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$",
            message = "Password must contain at least one letter and one number, and no special characters")
    @Column(columnDefinition = "varchar(40) not null")
    private String password;
}
