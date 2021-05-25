package net.project.covid_project.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import  net.project.covid_project.model.Patient; 
import org.springframework.stereotype.Repository;

@Repository
public interface CovidPatientRepository extends JpaRepository<Patient, Long>{

	Object findById(long patientid);
}
