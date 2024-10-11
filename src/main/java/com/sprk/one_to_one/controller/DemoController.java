package com.sprk.one_to_one.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprk.one_to_one.entity.Instructor;
import com.sprk.one_to_one.entity.InstructorDetails;
import com.sprk.one_to_one.repository.AppDao;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class DemoController {

    private final AppDao appDao;

    // Save Mapping
    @PostMapping("/save")
    public Instructor saveInstructor(@RequestBody Instructor instructor){

        InstructorDetails details = instructor.getInstructorDetails();
        details = appDao.saveInstructorDetails(details);

        instructor.setInstructorDetails(details);
        return appDao.saveInstructor(instructor);
    }

    // Get Mapping
    @GetMapping("/show-Instructor/{id}")
    public Instructor getInstructorById(@PathVariable int id){
        return null;
    }
}
