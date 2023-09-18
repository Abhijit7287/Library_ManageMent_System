package com.example.demo.RequestDTOs;

import lombok.Data;

@Data
public class UpdateAuthorDetails {

    private Integer authorId;
    private String name;
    private String penName;
}
