package com.sprk.one_to_one.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instructorId;

    private String firstName;

    private String lastName;

    private String phone;

    @OneToOne(/*cascade = {CascadeType.ALL, orphanRemoval = true}*/)       // One to one relationship/mapping
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetails instructorDetails;
}
