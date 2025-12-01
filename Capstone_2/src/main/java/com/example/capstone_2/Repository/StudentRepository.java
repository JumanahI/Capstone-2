package com.example.capstone_2.Repository;

import com.example.capstone_2.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findStudentById(Integer id);

    List<Student> findStudentByMajor(String major);

    @Query("select s from Student s where s.universityName=?1")
    List<Student> getStudentsByUniversityName(String universityName);
}
