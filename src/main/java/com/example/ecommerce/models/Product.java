package com.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String title;
    private double price;
    @JoinColumn
    @ManyToOne
    private Category category;
    private String description;
    private String image;

}
