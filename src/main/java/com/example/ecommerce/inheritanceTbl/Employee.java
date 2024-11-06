package com.example.ecommerce.inheritanceTbl;

import jakarta.persistence.*;

//@Entity
public class Employee extends Person {

    private double salary;

//    @OneToOne
//    @JoinColumn(name = "panCard_id")
    private PanCard panCard;

//    @ManyToOne
//    @JoinColumn(name = "dept_id")
    private Department department;

}
