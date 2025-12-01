package com.example.capstone_2.Repository;
import com.example.capstone_2.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Project findProjectById(Integer id);

    List<Project> findProjectByFiled(String filed);
    List<Project> findProjectByTitle(String title);


}
