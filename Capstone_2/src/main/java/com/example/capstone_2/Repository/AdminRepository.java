package com.example.capstone_2.Repository;

import com.example.capstone_2.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findAdminById(Integer id);
}
