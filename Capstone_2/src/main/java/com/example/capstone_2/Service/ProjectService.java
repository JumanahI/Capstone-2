package com.example.capstone_2.Service;

import com.example.capstone_2.Model.Project;
import com.example.capstone_2.Model.Student;
import com.example.capstone_2.Repository.ProjectRepository;
import com.example.capstone_2.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public boolean addProject(Project project){
        Student student = studentRepository.findStudentById(project.getStudentId());
        if(student == null){
            return false;
        }
        projectRepository.save(project);
        return true;
    }

    public int updateProject(Integer id,Project project){
        Student student = studentRepository.findStudentById(project.getStudentId());
        if(student == null ){
            return 1;
        }
        Project oldProject = projectRepository.findProjectById(id);
        if(oldProject == null){
            return 2;
        }
        oldProject.setTitle(project.getTitle());
        oldProject.setDescription(project.getDescription());
        oldProject.setStudentId(project.getStudentId());
        oldProject.setFiled(project.getFiled());
        oldProject.setGitHubUrl(project.getGitHubUrl());
        oldProject.setDocumentationUrl(project.getDocumentationUrl());
        projectRepository.save(oldProject);
        return 3;
    }


    public boolean deleteProject(Integer id){
        Project project = projectRepository.findProjectById(id);
        if(project == null){
            return false;
        }
        projectRepository.delete(project);
        return true;
    }

    public List<Project> getProjectByFiled(String filed){
        return projectRepository.findProjectByFiled(filed);
    }

    public List<Project> getProjectByTitle(String title){
        return projectRepository.findProjectByTitle(title);
    }

}
