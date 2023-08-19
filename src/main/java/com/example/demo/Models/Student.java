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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id ///used for saying it is a Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) ///for AutoGenerating Value
    private Integer roll_no;

    private String name;

    private Integer age;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private Department department;

    @Column(unique = true)
    private String email_id;

    public Integer getRoll_no() {
        return roll_no;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Department getDepartment() {
        return department;
    }

    public String getEmail_id() {
        return email_id;
    }

    public Library_Card getLibrary_card() {
        return library_card;
    }

    ///this is done for birectional mapping between student and library card
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private Library_Card library_card;

}





