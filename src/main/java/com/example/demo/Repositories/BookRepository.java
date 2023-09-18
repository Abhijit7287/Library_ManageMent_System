package com.example.demo.Repositories;

import com.example.demo.Enums.Genere;
import com.example.demo.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer>{

    @Query(value = "select * from book where genere=genere",nativeQuery = true)
    List<Book>findByGenere(Genere genere);

}
