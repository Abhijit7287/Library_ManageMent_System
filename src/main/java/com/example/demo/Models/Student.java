package com.example.demo.Models;

import com.example.demo.Enums.Department;
import com.example.demo.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id ///used for saying it is a Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) ///for AutoGenerating Value
    private int roll_no;

    private String name;

    private int age;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private Department department;

    @Column(unique = true)
    private String email_id;

}
