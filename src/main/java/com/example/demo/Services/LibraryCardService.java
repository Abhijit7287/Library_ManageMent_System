package com.example.demo.Services;

import com.example.demo.Models.Library_Card;
import com.example.demo.Models.Student;
import com.example.demo.Repositories.LibraryCardRepository;
import com.example.demo.Repositories.StudentRepository;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.annotation.ExceptionProxy;

import java.util.Optional;

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


    public String IssueToStudent(Integer cardNo , Integer rollNo) throws Exception {

        ///Validation
        ///Student should present
        if(!studentRepository.existsById(rollNo)){
            throw new Exception("Student roll_no is Invalid");
        }
        //check if Card is present
        if(!libraryCardRepository.existsById(cardNo)){
            throw new Exception("CardId is Invalid");
        }

        ///update the Foreign key
        Optional<Student> student  = studentRepository.findById(rollNo);

        Student studentObj = student.get();

        Optional<Library_Card> library_card  = libraryCardRepository.findById(cardNo);

        Library_Card libraryObj = library_card.get();


        ///set the student object in library card object
        libraryObj.setStudent(studentObj);

        ///set the library_card to a student
        studentObj.setLibrary_card(libraryObj);

        ///now save both of them , we will save student only library card will automatically
        ///get stored as they mapped with each other

        studentRepository.save(studentObj);

        return "Card is issued to a student";
    }



}
