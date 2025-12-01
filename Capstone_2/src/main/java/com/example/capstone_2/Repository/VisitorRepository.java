package com.example.capstone_2.Repository;

import com.example.capstone_2.Model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitorRepository extends JpaRepository<Visitor,Integer> {
    Visitor findVisitorById(Integer id);
}
