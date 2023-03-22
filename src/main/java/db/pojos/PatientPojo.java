package db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



public class PatientPojo implements Serializable { //Serializable is used to have things exist outside memory (in the computer)

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name; //for easy access when still do not have a "barcode reader"
	private Gender gender;
	private Integer age;
	private List<DiseasePojo> disease ; //as there's its brother List on Worker class, this conforms a many-to-many relationship 
	private List<DrugPojo> drugs; 
	private List<SymptomsPojo> symptoms ;
	
	
	public PatientPojo( String name, Gender gender, Integer age) {

		this.name = name;
		this.gender = gender;
		this.age = age;
		
	}
	
	public PatientPojo(Integer id, String name, Gender gender, Integer age) {

		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		
	}
	

	 public PatientPojo(String name, List<DiseasePojo> disease, List<DrugPojo> drugs, List<SymptomsPojo> symptoms) {
		super();
		this.name = name;
		this.disease = disease;
		this.drugs = drugs;
		this.symptoms = symptoms;
	}

	public PatientPojo(Integer id, String name, Gender gender, Integer age, List<DiseasePojo> disease,
			List<DrugPojo> drugs, List<SymptomsPojo> symptoms) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.disease = disease;
		this.drugs = drugs;
		this.symptoms = symptoms;
	}

	
	
	public PatientPojo(String name, Gender gender, Integer age, List<DiseasePojo> disease,
			List<DrugPojo> drugs, List<SymptomsPojo> symptoms) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.disease = disease;
		this.drugs = drugs;
		this.symptoms = symptoms;
	}
	
	
	//TIO ESTO ESTA MAL -> AHORA LO REVISAS NAT
	
    public boolean detectSym(String name) {
    	boolean compro= false;
    	for (int i=0; i< symptoms.size();i++) {
    		if (symptoms.get(i).getName().equals(name)) {
    			compro=true;
    		}
    	}
    	return compro;
    }
    
    public boolean detectDrug(String name) {
    	boolean compro= false;
    	for (int i=0; i< drugs.size();i++) {
    		if (drugs.get(i).getName().equals(name)) {
    			compro=true;
    		}
    	}
    	return compro;
    }
    
    public Integer detectSymSev(String name) {
    	Integer a=0;
    	for (int i=0; i< symptoms.size();i++) {
    		if (symptoms.get(i).getName().equals(name)) {
    			a=symptoms.get(i).getSever();
    		}
    		
    	}
    	return a;
    }
    
	public void editProb(String name, double points) {
        float point = (float) points;
		for (int i = 0; i < disease.size(); i++) {
			if (disease.get(i).getName().equals(name)) {

				disease.get(i).setScore(disease.get(i).getScore() + point);
				break;
			}

		}

	}
	
	public void negsToZero(){
		for (int i = 0; i < disease.size(); i++) {
			if (disease.get(i).getScore() < 0) {

				disease.get(i).setScore((float) 0);
			}		
	}
	


	
	public List<SymptomsPojo> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<SymptomsPojo> symptoms) {
		this.symptoms = symptoms;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<DiseasePojo> getDisease() {
		return disease;
	}

	public void setDisease(List<DiseasePojo> disease) {
		this.disease = disease;
	}

	public List<DrugPojo> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<DrugPojo> drugs) {
		this.drugs = drugs;
	}

	@Override
	public String toString() {
		return "PatientPojo [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", disease="
				+ disease + ", drugs=" + drugs + ", symptoms=" + symptoms + "]";
	}

}
