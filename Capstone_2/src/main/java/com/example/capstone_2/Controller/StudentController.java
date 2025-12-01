package com.example.capstone_2.Controller;

import com.example.capstone_2.Api.ApiResponse;
import com.example.capstone_2.Model.Student;
import com.example.capstone_2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/get")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = studentService.updateStudent(id, student);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student id not found"));
    }

    @GetMapping("/get-students-major/{major}")
    public ResponseEntity<?> getStudentsByMajor(@PathVariable String major){
        List<Student> studentList = studentService.getStudentsByMajor(major);
        if(studentList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no student with the given major"));
        }
        return ResponseEntity.status(200).body(studentList);
    }

    @GetMapping("/get-students-university-name/{universityName}")
    public ResponseEntity<?> getStudentsByUniversityName(@PathVariable String universityName){
        List<Student> studentList = studentService.getStudentsByUniversityName(universityName);
        if(studentList.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There is no student with the given university name"));
        }
        return ResponseEntity.status(200).body(studentList);
    }
}
