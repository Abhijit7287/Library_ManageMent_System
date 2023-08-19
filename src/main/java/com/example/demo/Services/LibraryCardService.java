package com.example.demo.Services;

import com.example.demo.Models.Library_Card;
import com.example.demo.Repositories.LibraryCardRepository;
import com.example.demo.Repositories.StudentRepository;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.annotation.ExceptionProxy;

@Service
public class LibraryCardService {


    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private StudentRepository studentRepository;


    public String addCard(Library_Card library_card){

        libraryCardRepository.save(library_card);
        return "Library card has been successfully added";
    }

    public String IssueToStudent(Integer cardNo , Integer rollNo) throws Exception{

        ///Validation
        ///Student should present
        if(!studentRepository.existsById(rollNo)){
            throw new Exception("Student roll_no is Invalid");
        }
        //check if Card is present
        if(!libraryCardRepository.existsById(cardNo)){
            throw new Exception("CardId is Invalid");
        }



    }



}
