package com.example.capstone_2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Request {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Investor ID cannot be null ")
    @Column(columnDefinition = "int not null")
    private Integer investorId;

    @NotNull(message = "Project ID cannot be null ")
    @Column(columnDefinition = "int not null")
    private Integer projectId;

    @NotEmpty(message = "Content cannot be empty")
    @Column(columnDefinition = "varchar(200) not null")
    private String content;

    @Pattern(regexp = "^(Accepted|Rejected|Pending)$",
            message = "Status must be Accepted, Rejected, or Pending")
    @Column(columnDefinition = "varchar(50) not null")
    private String status;

    @CreationTimestamp
    @Column(columnDefinition = "dateTime ")
    private LocalDate createdAt;
}
