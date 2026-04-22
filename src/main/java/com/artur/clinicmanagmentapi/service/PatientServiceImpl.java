package com.artur.clinicmanagmentapi.service;

import com.artur.clinicmanagmentapi.dao.PatientRepository;
import com.artur.clinicmanagmentapi.entity.Patient;
import com.artur.clinicmanagmentapi.rest.PatientNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient findById(int id) {
        Optional<Patient> result = patientRepository.findById(id);
        Patient tempPatient = new Patient();

        if(result.isPresent()){
            tempPatient = result.get();
        }
        else {
            throw new PatientNotFoundException("Patient with id: "+ id + " not found");
        }

        return tempPatient;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        patientRepository.deleteById(id);
    }
}
