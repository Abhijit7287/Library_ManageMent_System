package com.example.demo.Controllers;

import com.example.demo.Models.Library_Card;
import com.example.demo.Services.LibraryCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/card")
@Slf4j
public class CardController {

    @Autowired
private LibraryCardService libraryCardService;

    @PostMapping("/create")
    public ResponseEntity addCard(@RequestBody Library_Card card){

        try {
            libraryCardService.addCard(card);
            return new ResponseEntity("card has been added successfully", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/IssueToStudent")
    public ResponseEntity IssueToStudent(@RequestParam("cardId")Integer cardId,@RequestParam("roll_no")Integer roll_no){

        try{

        }catch (Exception e){

            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
