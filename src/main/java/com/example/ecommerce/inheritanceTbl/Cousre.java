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
public class Cousre extends Person {

    private String name;
    private String duration;
    //    @ManyToMany(mappedBy = "course")  // Define the inverse side of the relationship
//    @ManyToMany
//    @JoinTable(name = "course_student",
//            joinColumns = @JoinColumn(name = "course_Id"),
//            inverseJoinColumns = @JoinColumn(name = "student_Id"))
    private List<Student> student;
}



