package com.example.demo.Controllers;

import com.example.demo.Enums.Genere;
import com.example.demo.Models.Book;
import com.example.demo.RequestDTOs.AddBookDto;
import com.example.demo.ResponceDto.BookResponceDto;
import com.example.demo.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookDto addBookDto){

        try{
            bookService.addBook(addBookDto);
            return new ResponseEntity("Book has been added to Database successfully",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByGenere")
    public  ResponseEntity getByGenere(@RequestParam("genere")Genere genere){

        List<BookResponceDto>list = new ArrayList<>();

        list = bookService.findByGenere(genere);

        return new ResponseEntity(list,HttpStatus.OK);
    }




}
