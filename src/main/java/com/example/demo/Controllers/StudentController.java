package com.example.demo.Controllers;

import com.example.demo.Enums.Department;
import com.example.demo.Models.Student;
import com.example.demo.Services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {


    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addstudent(@RequestBody Student student){

        try{
            String Result = studentService.addStudent(student);
            return new ResponseEntity(Result,HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findDeptbyid")
    public ResponseEntity findDeptbyid(@RequestParam ("Id")Integer Id){

        try{
            Department dep = studentService.getDepartment(Id);
            return new ResponseEntity(dep,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
