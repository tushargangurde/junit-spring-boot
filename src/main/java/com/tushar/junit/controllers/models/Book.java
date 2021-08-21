package com.tushar.junit.controllers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Book {

    private Integer bookId;
    private String bookName;
    private Double bookPrice;

}
