package com.example.capstone_2.Controller;

import com.example.capstone_2.Api.ApiResponse;
import com.example.capstone_2.Model.Project;
import com.example.capstone_2.Service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @GetMapping("/get")
    public ResponseEntity<?> getProjects() {
        return ResponseEntity.status(200).body(projectService.getProjects());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isAdded = projectService.addProject(project);
        if(isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("Project added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student ID not fond"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Integer id, @RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int isUpdated = projectService.updateProject(id, project);
        if (isUpdated == 3) {
            return ResponseEntity.status(200).body(new ApiResponse("Project updated successfully"));
        } else if (isUpdated == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("Project id not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student ID not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Integer id) {
        boolean isDeleted = projectService.deleteProject(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Project deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Project id not found"));
    }

    @GetMapping("/get-project-category/{filed}")
    public ResponseEntity<?> getProjectByFiled(@PathVariable String filed){
        List<Project> projectList = projectService.getProjectByFiled(filed);
        if(projectList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no project with given filed"));
        }
        return ResponseEntity.status(200).body(projectList);
    }

    @GetMapping("/get-project-category/{title}")
    public ResponseEntity<?> getProjectByTitle(@PathVariable String title){
        List<Project> projectList = projectService.getProjectByTitle(title);
        if(projectList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no project with given title"));
        }
        return ResponseEntity.status(200).body(projectList);
    }
}
