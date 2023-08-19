package com.example.demo.Services;
import com.example.demo.Enums.Department;
import com.example.demo.Models.Student;
import com.example.demo.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student) throws Exception{

      if(student.getRoll_no()!=null){
          throw new Exception("Id should not be send as parameter");
      }

      studentRepository.save(student);

      return "Student has been added successfully";

    }

    public Department getDepartment(Integer Rollno) throws Exception{

        Optional<Student> optionalStudent = studentRepository.findById(Rollno);

        if(!optionalStudent.isPresent()){
            throw new Exception("Rollno is Incorrect");
        }

        Student student = optionalStudent.get();

        return student.getDepartment();
    }
}
