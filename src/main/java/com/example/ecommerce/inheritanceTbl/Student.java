package com.example.ecommerce.inheritanceTbl;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@Entity
@Getter
@Setter
public class Student extends Person {

//    @ManyToMany
//    @JoinTable(name = "student_course",
//            joinColumns = @JoinColumn(name = "student_Id"),
//            inverseJoinColumns = @JoinColumn(name = "course_Id"))
    private List<Cousre> course;

}
