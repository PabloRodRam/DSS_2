package db.pojos;

import java.io.Serializable;
import java.util.List;
import db.pojos.*;

public class DrugPojo implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name; 
	private List<SymptomsPojo> symptoms; //as there's its brother List on Worker class, this conforms a many-to-many relationship 
	private List<PatientPojo> patients; 
	
	
	
	
	public DrugPojo(Integer id, String name) {
		super();
		this.id= id;
		this.name = name;
	}

	
	public DrugPojo(String name) {
		super();
		
		this.name = name;
	}
	
	

	public DrugPojo(Integer id, String name, List<SymptomsPojo> symptoms, List<PatientPojo> patients) {
		super();
		this.id = id;
		this.name = name;
		this.symptoms = symptoms;
		this.patients = patients;
	}

	public List<SymptomsPojo> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<SymptomsPojo> symptoms) {
		this.symptoms = symptoms;
	}


	public List<PatientPojo> getPatients() {
		return patients;
	}


	public void setPatients(List<PatientPojo> patients) {
		this.patients = patients;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DrugPojo [id=" + id + ", name=" + name + ", symptoms=" + symptoms + ", patients=" + patients + "]";
	}
}
	