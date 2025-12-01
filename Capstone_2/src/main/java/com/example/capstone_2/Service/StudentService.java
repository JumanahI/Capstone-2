package com.example.capstone_2.Service;

import com.example.capstone_2.Model.Student;
import com.example.capstone_2.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student){
        Student oldStudent = student;
        studentRepository.save(oldStudent);
    }

    public boolean updateStudent(Integer id,Student student){
        Student oldStudent = studentRepository.findStudentById(id);
        if(oldStudent == null){
            return false;
        }
        oldStudent.setFullName(student.getFullName());
        oldStudent.setUsername(student.getUsername());
        oldStudent.setPassword(student.getPassword());
        oldStudent.setEmail(student.getEmail());
        oldStudent.setUniversityName(student.getUniversityName());
        oldStudent.setMajor(student.getMajor());
        studentRepository.save(oldStudent);
        return true;
    }

    public boolean deleteStudent(Integer id){
        Student student = studentRepository.findStudentById(id);
        if(student == null){
            return false;
        }
        studentRepository.delete(student);
        return true;
    }



    public List<Student> getStudentsByMajor(String major){
        return studentRepository.findStudentByMajor(major);
    }

    public List<Student> getStudentsByUniversityName(String universityName){
        return studentRepository.getStudentsByUniversityName(universityName);
    }
}
