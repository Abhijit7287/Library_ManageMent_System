package com.example.demo.Models;

import com.example.demo.Enums.Genere;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<TransactionOfStudent> transactionOfStudents = new ArrayList<>();
}
