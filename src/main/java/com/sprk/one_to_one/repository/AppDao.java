package com.sprk.one_to_one.repository;

import org.springframework.stereotype.Repository;

import com.sprk.one_to_one.entity.Instructor;

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
}
