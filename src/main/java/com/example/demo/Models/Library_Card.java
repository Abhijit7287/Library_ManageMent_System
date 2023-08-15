package com.example.demo.Models;

import com.example.demo.Enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Library_Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int card_no;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private int noOfBooksIssued;


    @OneToOne
    @JoinColumn
    private Student student;

}
