package com.sprk.one_to_one.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

    // Save Mapping
    @PostMapping("/save")
    public Instructor saveInstructor(@RequestBody Instructor instructor){

        InstructorDetails details = instructor.getInstructorDetails();
        details = appDao.saveInstructorDetails(details);

        instructor.setInstructorDetails(details);
        return appDao.saveInstructor(instructor);
    }

    // Get Mapping to get instructor by id
    @GetMapping("/showInstructors/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable int id){

        Instructor instructor = appDao.findByInstructorId(id);

        if(instructor == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(instructor);
    }

    // Get mapping to get list of all instructors
    @GetMapping("/instructors")
    public ResponseEntity<List<Instructor>> getAllInstructors(){

        List<Instructor> instructors = appDao.getAllInstructors();

        return ResponseEntity.ok(instructors);
    }

    // delete mapping for deletion of instructor details
    @DeleteMapping("/instructor/{id}")
    public ResponseEntity<Void> deleteInstructorById(@PathVariable int id){

        Instructor instructor = appDao.findByInstructorId(id);
    
        if(instructor == null){
            return ResponseEntity.notFound().build();   // Id not found
        }

        appDao.deleteInstructorById(id);
        return ResponseEntity.noContent().build();   // It will show successfull deletion 
        
    }

    // update mapping to change the details
    @PutMapping("/updateInstructor/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable int id, @RequestBody Instructor instructor){

        Instructor ExistingInstructor = appDao.findByInstructorId(id);

        if(ExistingInstructor == null){
            return ResponseEntity.notFound().build();
        }

        // updating instructor and instructorDetails fields
        ExistingInstructor.setFirstName(instructor.getFirstName());
        ExistingInstructor.setLastName(instructor.getLastName());
        ExistingInstructor.setPhone(instructor.getPhone());

        // logic to get details of instructor details
        if(instructor.getInstructorDetails() != null){
            InstructorDetails updatedDetails = instructor.getInstructorDetails();  // save the instructor details

            if(updatedDetails.getInstructorDetailId() == 0){        // Get the id of instructorDetails
                appDao.saveInstructorDetails(updatedDetails);
            }else{
                appDao.updateInstructorDetails(updatedDetails);
            }

            ExistingInstructor.setInstructorDetails(updatedDetails);
        }
        
        Instructor instructor2 = appDao.updateInstructorById(ExistingInstructor);
        return ResponseEntity.ok(instructor2);
    }
}
