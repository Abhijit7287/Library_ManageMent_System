package com.example.demo.Models;

import com.example.demo.Enums.Genere;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer book_id;

   private String title;

   private Boolean isAvailable;

   @Enumerated(value = EnumType.STRING)
   private Genere genere;

   private Date publication_date;

   private Integer price;

   ///this is a unidirectional mapping
    @ManyToOne
    @JoinColumn
    private Author author;
}
