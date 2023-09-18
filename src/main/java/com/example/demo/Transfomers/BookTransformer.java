package com.example.demo.Transfomers;

import com.example.demo.Models.Book;
import com.example.demo.RequestDTOs.AddBookDto;

public class BookTransformer {

    public static Book createBook(AddBookDto addBookDto){

        Book book  = Book.builder().title(addBookDto.getTitle()).genere(addBookDto.getGenere())
                .isAvailable(addBookDto.getIsAvailable()).price(addBookDto.getPrice())
                .publication_date(addBookDto.getPublicationDate()).build();

        return book;
    }
}
