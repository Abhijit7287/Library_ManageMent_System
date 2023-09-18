package com.example.demo.Services;

import com.example.demo.Enums.Genere;
import com.example.demo.Models.Author;
import com.example.demo.Models.Book;
import com.example.demo.Repositories.AuthorRepository;
import com.example.demo.Repositories.BookRepository;
import com.example.demo.RequestDTOs.AddBookDto;
import com.example.demo.ResponceDto.BookResponceDto;
import com.example.demo.Transfomers.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(AddBookDto addBookDto) throws Exception{

        Optional<Author> author = authorRepository.findById(addBookDto.getAuthorId());

        if(!author.isPresent()){
            throw new Exception("Author id is Incorrect");
        }

        Author authorObj = author.get();

        Book book = BookTransformer.createBook(addBookDto);


        //Got the book Object

        //Set the FK variables

        //Since it's a bidirectional : need to set both in child and parent class

        //Set the parent entity in child class

        book.setAuthor(authorObj);

        List<Book> list = authorObj.getBookList();

        list.add(book);

        authorObj.setBookList(list);

        //I need to save them :-->

        //Save only the parent : child will get automatically saved
        authorRepository.save(authorObj);

        return "Book has Successfully added in the Database";
    }

    public List<BookResponceDto> findByGenere(Genere genere){

        List<Book> list = bookRepository.findByGenere(genere);

        List<BookResponceDto> ans = new ArrayList<>();

        for(Book b : list){

            BookResponceDto bookResponceDto = BookResponceDto.builder().authorName(b.getAuthor().getName())
                    .genere(b.getGenere()).dateOfPublication(b.getPublication_date()).isAvailable(b.getIsAvailable())
                    .price(b.getPrice()).title(b.getTitle()).build();

            ans.add(bookResponceDto);
        }

        return ans;
    }



}
