package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetSingleProductDTO {

    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
