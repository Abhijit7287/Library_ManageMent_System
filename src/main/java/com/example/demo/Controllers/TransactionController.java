package com.example.demo.Controllers;

import com.example.demo.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController{

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity issueBook(@RequestParam("bookId")Integer bookId,@RequestParam("cardId")Integer cardId){

        try{
          String s =  transactionService.issueBook(bookId,cardId);
          return new ResponseEntity(s,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity returnBook(@RequestParam("bookId")Integer bookId,@RequestParam("cardId")Integer cardId){

        try{
            String s = transactionService.returnBook(bookId,cardId);
            return new ResponseEntity(s,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
