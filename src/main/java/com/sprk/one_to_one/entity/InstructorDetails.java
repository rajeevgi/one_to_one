package com.sprk.one_to_one.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Details")
public class InstructorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instructorDetailId;

    private String hobby;

    private String qualification;
}
