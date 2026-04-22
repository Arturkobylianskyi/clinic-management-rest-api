package com.artur.clinicmanagmentapi.rest;

import com.artur.clinicmanagmentapi.entity.Patient;
import com.artur.clinicmanagmentapi.service.PatientService;
import com.artur.clinicmanagmentapi.service.PatientServiceImpl;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class PatientRestController {

    private final JsonMapper jsonMapper;
    public PatientService patientService;

    @Autowired
    public PatientRestController(PatientService patientService, JsonMapper jsonMapper) {
        this.patientService = patientService;
        this.jsonMapper = jsonMapper;
    }

    @GetMapping("/patients")
    public List<Patient> getPatients(){
        return patientService.findAll();
    }

    @GetMapping("/patients/{patientId}")
    public Patient getPatient(@PathVariable int patientId){
        Patient tempPatient = patientService.findById(patientId);

        return tempPatient;
    }

    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient){
        patient.setId(0);

        Patient dbPatient = patientService.save(patient);
        return dbPatient;
    }

    @PatchMapping("/patients/{patientId}")
    public Patient patchPatient(@PathVariable int patientId,
                                @RequestBody Map<String, Object> patchPayload){

        Patient tempPatient = patientService.findById(patientId);

        if(tempPatient==null){
            throw new PatientNotFoundException("Patient with id: "+ patientId + " not found");
        }
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Id is not allowed in request");
        }

        Patient patchedPatient = jsonMapper.updateValue(tempPatient, patchPayload);
        Patient dbPatient = patientService.save(patchedPatient);

        return dbPatient;
    }

    @PutMapping("/patients")
    public Patient updatePatient(@RequestBody Patient patient){
        Patient dbPatient = patientService.save(patient);

        return dbPatient;
    }

    @DeleteMapping("/patients/{patientId}")
    public String deletePatient(@PathVariable int patientId){
        patientService.deleteById(patientId);

        return "Patient with id: " + patientId +"was deleted";
    }

}
