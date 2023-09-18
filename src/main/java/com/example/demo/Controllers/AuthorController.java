package com.example.demo.Controllers;


import com.example.demo.Models.Author;
import com.example.demo.Repositories.AuthorRepository;
import com.example.demo.RequestDTOs.UpdateAuthorDetails;
import com.example.demo.Services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@Slf4j
public class AuthorController {


    @Autowired
    AuthorService authorService;


    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody Author author) throws Exception{

        try{
           authorService.addAuthor(author);
           return new ResponseEntity("Author has been added successfully", HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAuthor")
    public ResponseEntity getAuthor(@RequestParam("authorId")Integer authorId){

        try{
           Author author = authorService.findAuthor(authorId);
            return new ResponseEntity(author,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateDetails")
    public ResponseEntity updateDetails(UpdateAuthorDetails updateAuthorDetails){

        try{
            String s = authorService.updateDetails(updateAuthorDetails);
            return new ResponseEntity(s,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
 }
