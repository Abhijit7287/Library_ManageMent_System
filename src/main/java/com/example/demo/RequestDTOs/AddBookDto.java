package com.example.demo.RequestDTOs;

import com.example.demo.Enums.Genere;
import lombok.Data;

import java.util.Date;

@Data
public class AddBookDto {

    private String title;
    private Boolean isAvailable;
    private Genere genere;
    private Date publicationDate;
    private Integer price;
    private Integer authorId;

}
