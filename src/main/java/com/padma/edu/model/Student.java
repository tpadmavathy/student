package com.padma.edu.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class Student {

    private String name;
    private int age;
    @Id
    private Integer roll_no;
    private char gender;
    private String address;
    private char grade;


    public Student(String name, int age, Integer roll_no, char gender, String address, char grade) {
        this.name = name;
        this.age = age;
        this.roll_no = roll_no;
        this.gender = gender;
        this.address = address;
        this.grade = grade;
    }


}
