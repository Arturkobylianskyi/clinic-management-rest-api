package com.artur.clinicmanagmentapi.service;

import com.artur.clinicmanagmentapi.dao.PatientDAO;
import com.artur.clinicmanagmentapi.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    private final PatientDAO patientDAO;

    @Autowired
    public PatientServiceImpl(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Transactional
    @Override
    public Patient save(Patient patient) {
        return patientDAO.save(patient);
    }

    @Override
    public Patient findById(int id) {
        return patientDAO.findById(id);
    }

    @Override
    public List<Patient> findAll() {
        return patientDAO.findAll();
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        patientDAO.deleteById(id);
    }
}
