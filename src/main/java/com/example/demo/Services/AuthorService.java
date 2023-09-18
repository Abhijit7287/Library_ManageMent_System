package com.example.demo.Services;

import com.example.demo.Models.Author;
import com.example.demo.Repositories.AuthorRepository;
import com.example.demo.RequestDTOs.UpdateAuthorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    public String addAuthor(Author author) throws Exception {

        if(author.getAuthor_id()!=null){
            throw new Exception("author_id should not be send as parameter");
        }

        authorRepository.save(author);

        return "Author has been added to Database successfully";
    }

    public Author findAuthor(Integer authorId) throws Exception{

        Optional<Author> author = authorRepository.findById(authorId);

        if(!author.isPresent()){
            throw new Exception("authorId is incorrect");
        }

        return author.get();
    }

    public String updateDetails(UpdateAuthorDetails updateAuthorDetails) throws Exception {

        Optional<Author> author = authorRepository.findById(updateAuthorDetails.getAuthorId());

        if(!author.isPresent()){
            throw new Exception("authorId is Incorrect");
        }

        Author authorObj = author.get();

        authorObj.setName(updateAuthorDetails.getName());

        authorObj.setPen_name(updateAuthorDetails.getPenName());

        authorRepository.save(authorObj);

        return "Author object updated successfully";

    }
}
