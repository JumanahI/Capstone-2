package com.example.capstone_2.Repository;

import com.example.capstone_2.Model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvestorRepository extends JpaRepository<Investor,Integer> {
    Investor findInvestorById(Integer id);
}
