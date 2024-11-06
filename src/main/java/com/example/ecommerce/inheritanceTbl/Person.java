package com.example.ecommerce.inheritanceTbl;

import jakarta.persistence.*;

//@MappedSuperclass
public class Person {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

}
