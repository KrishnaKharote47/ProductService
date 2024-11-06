package com.example.ecommerce.inheritanceTbl;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

//@Entity
public class Department extends Person {


    private String branch;

    @OneToMany(mappedBy = "department")
    private List<Employee> employee;


}
