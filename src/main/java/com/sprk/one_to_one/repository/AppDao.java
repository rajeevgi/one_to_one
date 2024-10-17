package com.sprk.one_to_one.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.sprk.one_to_one.entity.Instructor;
import com.sprk.one_to_one.entity.InstructorDetails;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AppDao {

    // @PersistenceContext
    private final EntityManager entityManager;

    // Save Mapping to add instructor
    @Transactional
    public Instructor saveInstructor(Instructor instructor) {
        return entityManager.merge(instructor);
    }

    // Save mapping to add instructorDetail
    @Transactional
    public InstructorDetails saveInstructorDetails(InstructorDetails instructorDetails) {
        return entityManager.merge(instructorDetails);
    }

    // Get mapping to fetch Instructor by Id
    @Transactional
    public Instructor findByInstructorId(int id) {
        // TODO Auto-generated method stub
        return entityManager.find(Instructor.class, id);
    }

    // Get Mapping to fetch InstructorDetail by Id
    public InstructorDetails findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetails.class, id);
    }

    // Get mapping to get fetch List of Instructors
    @Transactional
    public List<Instructor> getAllInstructors() {
        return entityManager.createQuery("select i from Instructor i", Instructor.class).getResultList();
    }

    // Delete mapping to remove instructor by Id
    @Transactional
    public String deleteInstructorById(int id) {

        Instructor instructor = findByInstructorId(id);

        if (instructor != null) {
            entityManager.remove(instructor);
            return "Deleted Successfully";
        }else{
            return "Something went wrong";
        }
    }

    // Delete mapping for InstructorDetail
    @Transactional
    public String deleteInstructorDetailById(int id) {
        
        InstructorDetails instructorDetails = entityManager.find(InstructorDetails.class, id);

        if(instructorDetails != null){
            Instructor instructor = instructorDetails.getInstructor();
            instructor.setInstructorDetails(null);
            entityManager.remove(instructorDetails);
            return "Delete Successfully";
        }else{
            return " Something went wrong";
        }

    }


    // Put Mapping to update info of Instructor and InstructorDetail by Id
    @Transactional
    public Instructor updateInstructorById(Instructor instructor) {
        return entityManager.merge(instructor);
    }

    @Transactional
    public InstructorDetails updateInstructorDetails(InstructorDetails instructorDetails){
        return entityManager.merge(instructorDetails);
    }

    @Transactional
    public Instructor updateInstructorDetails(int id, InstructorDetails instructorDetails) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        if(instructor != null){
            instructor.setInstructorDetails(instructorDetails);
            return entityManager.merge(instructor);
        }else{
            return null;
        }
    }

}
