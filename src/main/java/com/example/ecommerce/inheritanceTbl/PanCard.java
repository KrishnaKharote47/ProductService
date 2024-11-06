package com.example.ecommerce.inheritanceTbl;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

//@Entity
public class PanCard extends Person {

    private String panNo;

//    @OneToOne
//    @JoinColumn(name="employee_id")
    private Employee employee;
}
