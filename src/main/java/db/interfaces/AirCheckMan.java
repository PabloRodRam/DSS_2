package db.interfaces;

import java.util.List;

import db.pojos.DiseasePojo;
import db.pojos.DrugPojo;
import db.pojos.PatientPojo;
import db.pojos.SymptomsPojo;

public interface AirCheckMan {
	
	public void connect();
	public void disconnect();
	
	public void addSymptom(SymptomsPojo symptom);
	public List<SymptomsPojo> listSymptoms();
	
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
	
	public void addDrug(DrugPojo drug);
	public List<DrugPojo> listDrugs();
	public void addDisease(DiseasePojo disease);
	public List<DiseasePojo> listDiseases();
}
