package com.artur.clinicmanagmentapi.dao;

import com.artur.clinicmanagmentapi.entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDAOImpl implements PatientDAO{

    private final EntityManager entityManager;

    @Autowired
    public PatientDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Patient save(Patient patient) {
        Patient dbPatient = entityManager.merge(patient);
        return dbPatient;
    }

    @Override
    public Patient findById(int id) {
        Patient dbPatient = entityManager.find(Patient.class, id);

        return dbPatient;
    }

    @Override
    public List<Patient> findAll() {
        TypedQuery<Patient> theQuery = entityManager.createQuery("FROM Patient", Patient.class);
        return theQuery.getResultList();
    }

    @Override
    public void deleteById(int id) {
        Patient thePatient = entityManager.find(Patient.class, id);
        entityManager.remove(thePatient);
    }
}
