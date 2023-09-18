package com.example.demo.ResponceDto;

import com.example.demo.Enums.Genere;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BookResponceDto {

    private String title;

    private Boolean isAvailable;

    private Genere genere;

    private Date dateOfPublication;

    private Integer price;

    private String authorName;
}
