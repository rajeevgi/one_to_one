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

    private final EntityManager entityManager;

    @Transactional
    public Instructor saveInstructor(Instructor instructor){
        return entityManager.merge(instructor);
    }

    @Transactional
    public InstructorDetails saveInstructorDetails(InstructorDetails instructorDetails){
        return entityManager.merge(instructorDetails);
    }

    @Transactional
    public void deleteInstructor(int id){

        Instructor instructor = findByInstructorId(id);
        entityManager.remove(instructor);
    }

    public Instructor findByInstructorId(int id) {
        // TODO Auto-generated method stub
        return entityManager.find(Instructor.class, id);
    }

    public List<Instructor> getAllInstructor(){
        return (List<Instructor>) entityManager.find(Instructor.class, entityManager);
    }

    @Transactional
    public Instructor updateInstructor(int id){

        Instructor instructor = findByInstructorId(id);

        return entityManager.merge(instructor);
    }
}
