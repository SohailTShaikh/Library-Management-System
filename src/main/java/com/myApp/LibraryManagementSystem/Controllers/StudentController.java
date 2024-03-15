package com.myApp.LibraryManagementSystem.Controllers;


import com.myApp.LibraryManagementSystem.Entities.Student;
import com.myApp.LibraryManagementSystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
      String result=studentService.addStudent(student);
      return result;
    }
}
