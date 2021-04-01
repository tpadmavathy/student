package com.padma.edu.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode @AllArgsConstructor
@ApiModel
public class Student {

    @ApiModelProperty(notes = "Name of the Student")
    private String name;
    @ApiModelProperty(notes = "Age of the Student")
    private int age;
    @Id
    @ApiModelProperty(notes = "Roll no of the Student")
    private Integer roll_no;
    @ApiModelProperty(notes = "Gender of the Student", allowableValues = "M, F")
    private char gender;
    @ApiModelProperty(notes = "Address of the Student")
    private String address;
    @ApiModelProperty(notes = "Grade of the Student", allowableValues = "A, B, C, D")
    private char grade;


}
