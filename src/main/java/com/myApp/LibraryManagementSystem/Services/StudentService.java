package com.myApp.LibraryManagementSystem.Services;


import com.myApp.LibraryManagementSystem.Entities.Student;
import com.myApp.LibraryManagementSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public String addStudent(Student student){
         studentRepository.save(student);
         return "Student has been saved to the database";
    }


}
