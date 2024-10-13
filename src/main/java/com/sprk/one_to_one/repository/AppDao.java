package com.sprk.one_to_one.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.sprk.one_to_one.entity.Instructor;
import com.sprk.one_to_one.entity.InstructorDetails;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AppDao {

    @PersistenceContext
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
    public Instructor findByInstructorId(int id) {
        // TODO Auto-generated method stub
        return entityManager.find(Instructor.class, id);
    }

    @Transactional
    public List<Instructor> getAllInstructors(){
        return entityManager.createQuery("select i from Instructor i", Instructor.class).getResultList();
    }

    @Transactional
    public void deleteInstructorById(int id){

        Instructor instructor = entityManager.find(Instructor.class, id);

        if(instructor != null){
            entityManager.remove(instructor);
        }
    }

    @Transactional
    public Instructor updateInstructorById(Instructor instructor){

        // Instructor instructor = findByInstructorId(id);
        
        return entityManager.merge(instructor);
    }
}
