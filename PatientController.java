package net.project.covid_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
//import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.project.covid_project.repository.CovidPatientRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import net.project.covid_project.model.Patient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import net.project.covid_project.Exception.ResourceNotFoundException;
import net.project.covid_project.controller.PatientController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/V1")
public class PatientController{
	@Autowired
	private CovidPatientRepository covidpatientRepository;

	@GetMapping("/patient")
	public List<Patient> getAllPatient(){
		return covidpatientRepository.findAll();
	}
	
	@PostMapping("/patient")
	public void createPatient(@RequestBody Patient patient){
		 covidpatientRepository.save(patient);
	}
	
	@GetMapping("/patient/{id}")
	//@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Patient> getPatientById(@PathVariable(value = "id") Long patientid) throws ResourceNotFoundException{
		Patient patient = covidpatientRepository.findById(patientid).orElseThrow(() -> new ResourceNotFoundException("patient not found" + patientid));
		return ResponseEntity.ok().body(patient);
	}

	@PutMapping("/patient/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") Long patientid,@RequestBody Patient patientDetails) throws ResourceNotFoundException{
		Patient patient = covidpatientRepository.findById(patientid).orElseThrow(() -> new ResourceNotFoundException("patient not found" + patientid));
		patient.setFirstname(patientDetails.getFirstname());
		patient.setLastname(patientDetails.getLastname());
		patient.setemailid(patientDetails.getemailid());
		
		final Patient updatePatient = covidpatientRepository.save(patient);
		return ResponseEntity.ok(updatePatient);		
	}

	@DeleteMapping("/patient/{id}")
	public Map<String, Boolean> deletePatient(@PathVariable(value = "id") Long patientid) throws ResourceNotFoundException{
		covidpatientRepository.findById(patientid).orElseThrow(() -> new ResourceNotFoundException("patient not found" + patientid));
		covidpatientRepository.deleteById(patientid);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		//return ResponseEntity.ok().bulid();
		return response;
	}
}

