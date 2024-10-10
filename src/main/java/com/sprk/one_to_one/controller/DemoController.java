package com.sprk.one_to_one.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprk.one_to_one.entity.Instructor;
import com.sprk.one_to_one.repository.AppDao;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class DemoController {

    private final AppDao appDao;

    @PostMapping("/save")
    public Instructor saveInstructor(@RequestBody Instructor instructor){
        return appDao.saveInstructor(instructor);
    }
}
