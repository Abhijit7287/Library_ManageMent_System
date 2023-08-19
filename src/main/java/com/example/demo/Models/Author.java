package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer author_id;

    private String name;

    private String email_id;

    private Integer age;

    private String pen_name;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    List<Book> bookList = new ArrayList<>();

}
