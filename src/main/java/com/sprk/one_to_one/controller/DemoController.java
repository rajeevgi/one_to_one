package com.sprk.one_to_one.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // Post Mapping to add instructor and InstructorDetail
    @PostMapping("/save")
    public Instructor saveInstructor(@RequestBody Instructor instructor) {
        return appDao.saveInstructor(instructor);
    }

    @PostMapping("/addDetail")
        public Instructor saveInstructorDetail(@RequestBody InstructorDetails instructorDetails){
            Instructor instructor = instructorDetails.getInstructor();
            instructor.setInstructorDetails(instructorDetails);

            return appDao.saveInstructor(instructor);
    }

    // Get Mapping to get instructor by id
    @GetMapping("/showInstructors/{id}")
    public Instructor getInstructorById(@PathVariable int id) {

        return appDao.findByInstructorId(id);
    }

    // Get InstructorDetail by Id
    @GetMapping("/getInstructorDetail/{id}")
    public InstructorDetails getInstructorDetailsById(@PathVariable int id){
        return appDao.findInstructorDetailById(id);
    }

    // Get mapping to get list of all instructors
    @GetMapping("/instructors")
    public List<Instructor> getAllInstructors() {

        List<Instructor> instructors = appDao.getAllInstructors();

        return instructors;

    }

    // delete mapping for Instructor 
    @DeleteMapping("/instructor/{id}")
    public String deleteInstructorById(@PathVariable int id) {

        String message = appDao.deleteInstructorById(id);

        return message;

    }

    // Delete mapping for InstructorDetail
    @DeleteMapping("/instructorDetailDelete/{id}")
    public String deleteInstructorDetailById(@PathVariable int id){

        String message = appDao.deleteInstructorDetailById(id);

        return message;
    }

    // update mapping to change the details
    @PutMapping("/updateInstructor/{id}")
    public Instructor updateByInstructorId(@PathVariable int id, @RequestBody Instructor instructor){
        Instructor savedInstructor = appDao.findByInstructorId(id);

        if(savedInstructor != null){
            InstructorDetails updateInstructorDetails = instructor.getInstructorDetails();

            updateInstructorDetails.setInstructorDetailId(savedInstructor.getInstructorDetails().getInstructorDetailId());

            instructor.setInstructorId(savedInstructor.getInstructorId());
            instructor.setInstructorDetails(updateInstructorDetails);

            return appDao.saveInstructor(instructor);
        }else{
            return null;
        }
    }

    // update mapping for instructor details which is deleted
    @PutMapping("/updateInstructorDetail/{id}")
    public Instructor updateInstructorDetails(@RequestBody InstructorDetails instructorDetails,@PathVariable int id){

        return appDao.updateInstructorDetails(id, instructorDetails);       
    }
}
