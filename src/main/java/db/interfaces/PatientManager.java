package db.interfaces;
import java.util.List;

import db.pojos.*;


public interface PatientManager {

	public void addPatient(PatientPojo patient);
	public List<PatientPojo> listPatients();
	void addSymptomsToPatient(PatientPojo patient, SymptomsPojo symptom);
	void addDrugsToPatient(PatientPojo patient, DrugPojo drug);
	void addDiseasesToPatient(PatientPojo patient, DiseasePojo disease);
	
	public List<Integer> listPatientsSymptomsById(Integer id_patient);
	public List<SymptomsPojo> listSymptomsByName(List<Integer> thePatientSymptoms );
	public List<Integer> listPatientsDrugsById(Integer id_patient);
	public List<DrugPojo> listDrugsByName(List<Integer> theDrugsByName );
	public List<Integer> listPatientsDiseasesById(Integer id_patient);
	public List<DiseasePojo> listDiseasesByName(List<Integer> theDiseasesByName );
	
	

}
