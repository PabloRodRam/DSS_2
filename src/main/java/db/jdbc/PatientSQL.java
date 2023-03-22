package db.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.PatientManager;
import db.pojos.*;

public class PatientSQL implements PatientManager{

	

	@Override
	public void addPatient(PatientPojo patient) {
		
		try {

			String sql = "INSERT INTO Patient (name, gender, age )";
			sql+= " VALUES (?,?,?)";
			PreparedStatement pstmt = DBManagerSQL.c.prepareStatement(sql); 
			
			//pstmt.setInt(1, patient.getId());
			pstmt.setString(1,patient.getName().toString());
			pstmt.setString(2,patient.getGender().toString());
			pstmt.setInt(3,patient.getAge());

			
			pstmt.executeUpdate();
			pstmt.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}//addPatient
	
	
	public List<PatientPojo> listPatients() { //we show all the animals in the database
		List<PatientPojo> thePatients = new ArrayList<PatientPojo>();
		
		try {	
			
			String sql = "SELECT * FROM Patient "; 			
			PreparedStatement prep = DBManagerSQL.c.prepareStatement(sql);	
			ResultSet rs = prep.executeQuery();
		
		while (rs.next()) { 
			int id = rs.getInt("id");	
			String name = rs.getString("name");
			String gender = rs.getString("gender"); //Revisar esto pq es un ENUM
			int age = rs.getInt("age"); 
			
			
			Gender realGender = null ; //ENUM
			
			if(rs.getString("gender").equalsIgnoreCase("FEMEMINE")) {
				realGender = Gender.FEMENINE;
			}else {
				if(rs.getString("gender").equalsIgnoreCase("MASCULINE")) {
					realGender = Gender.MASCULINE;
			}}

			
			PatientPojo unpatient = new PatientPojo(id, name, realGender, age);
			thePatients.add(unpatient);
			
			}

		prep.close();
		rs.close();
		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return thePatients;
	}
	
	
	
	
	//NUEVO A PONER EN EL NUEVO PROYECTO
	
		//ADD SYMPTOMS TO A SPECIFIC PATIENT
		@Override
		public void addSymptomsToPatient(PatientPojo patient, SymptomsPojo symptom) {
			
			try {
	
				String sql = "INSERT INTO patient_symptom (patient_id, symptom_id )";
				sql+= " VALUES (?,?)";
				PreparedStatement pstmt = DBManagerSQL.c.prepareStatement(sql); 
				
				//System.out.println("Paciente en SQL:"+patient+"\n");
				pstmt.setInt(1,patient.getId());
				pstmt.setInt(2,symptom.getId());

				//System.out.println("Sintoma en SQL:"+symptom+" +id:"+symptom.getId()+"\n");
	
				
				pstmt.executeUpdate();
				pstmt.close();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
		}//addSymptomsToPatient
	
	
		
		
		//RETURN PATIENTS BY ID OR BY NAME
		public List<Integer> listPatientsSymptomsById(Integer id_patient) { //Mostramos los sintomas de un paciente especifico
			
			List<Integer> thePatientSymptomsById = new ArrayList<Integer>();
			
			try {	
				
				String sql = "SELECT * FROM patient_symptom WHERE patient_id LIKE ?"; 			
				PreparedStatement prep = DBManagerSQL.c.prepareStatement(sql);	
				ResultSet rs = prep.executeQuery();
			
			while (rs.next()) { 
				int symptom_id = rs.getInt("symptom_id");
				thePatientSymptomsById.add(symptom_id);
		}
			
			prep.close();
			rs.close();
			
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			return thePatientSymptomsById;
		}
		
		
		
		public List<SymptomsPojo> listSymptomsByName(List<Integer> thePatientSymptoms ) { //Buscamos el sintoma que es según el id q le pasemos del paciente
			
			List<SymptomsPojo> thePatientSymptomsByName = new ArrayList<SymptomsPojo>();
			
			try {	
				String sql = "SELECT * FROM Symptom WHERE id LIKE ?"; 			
				PreparedStatement prep = DBManagerSQL.c.prepareStatement(sql);	
				ResultSet rs = prep.executeQuery();
			
			while (rs.next()) { 
				String name = rs.getString("name");
				SymptomsPojo symptom = new SymptomsPojo(name);
				thePatientSymptomsByName.add(symptom);
		}
			
			prep.close();
			rs.close();
			
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			return thePatientSymptomsByName;
		}


		//RETURN DRUGS BY ID OR BY NAME
		
		public List<Integer> listPatientsDrugsById(Integer id_patient) { //Mostramos las medicinas q toma un paciente especifico
			
			List<Integer> thePatientDrugsById = new ArrayList<Integer>();
			
			try {	
				
				String sql = "SELECT * FROM patient_drug WHERE patient_id LIKE ?"; 			
				PreparedStatement prep = DBManagerSQL.c.prepareStatement(sql);	
				ResultSet rs = prep.executeQuery();
			
			while (rs.next()) { 
				int drug_id = rs.getInt("drug_id");
				thePatientDrugsById.add(drug_id);
		}
			
			prep.close();
			rs.close();
			
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			return thePatientDrugsById;
		}
		
		
		//ADD DRUGS TO A SPECIFIC PATIENT
		@Override
		public void addDrugsToPatient(PatientPojo patient, DrugPojo drug) {
			
			try {
	
				String sql = "INSERT INTO patient_drug (patient_id, drug_id )";
				sql+= " VALUES (?,?)";
				PreparedStatement pstmt = DBManagerSQL.c.prepareStatement(sql); 
				
				//pstmt.setInt(1, patient.getId());
				pstmt.setInt(1,patient.getId());
				pstmt.setInt(2,drug.getId());
	
				
				pstmt.executeUpdate();
				pstmt.close();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
		}//addSymptomsToPatient
		
		
		
		public List<DrugPojo> listDrugsByName(List<Integer> theDrugsByName ) { //Buscamos la medicina que es según el id q le pasemos del paciente
			
			List<DrugPojo> thePatientDrugsByName = new ArrayList<DrugPojo>();
			
			try {	
				String sql = "SELECT * FROM Drugs WHERE id LIKE ?"; 			
				PreparedStatement prep = DBManagerSQL.c.prepareStatement(sql);	
				ResultSet rs = prep.executeQuery();
			
			while (rs.next()) { 
				String name = rs.getString("name");
				DrugPojo drug = new DrugPojo(name);
				thePatientDrugsByName.add(drug);
		}
			
			prep.close();
			rs.close();
			
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			return thePatientDrugsByName;
		}


		
		
		
		//ADD DISEASE TO A SPECIFIC PATIENT
				@Override
				public void addDiseasesToPatient(PatientPojo patient, DiseasePojo disease) {
					
					try {
			
						String sql = "INSERT INTO patient_symptom (patient_id, disease_id )";
						sql+= " VALUES (?,?)";
						PreparedStatement pstmt = DBManagerSQL.c.prepareStatement(sql); 
						
						//pstmt.setInt(1, patient.getId());
						pstmt.setInt(1,patient.getId());
						pstmt.setInt(2,disease.getId());
			
						
						pstmt.executeUpdate();
						pstmt.close();
					
					} catch(Exception e) {
						e.printStackTrace();
					}
					// TODO Auto-generated method stub
				}//addSymptomsToPatient
				
				
		//RETURN DISEASES BY ID OR BY NAME

		public List<Integer> listPatientsDiseasesById(Integer id_patient) { //Mostramos las medicinas q toma un paciente especifico
			
			List<Integer> thePatientDiseasesById = new ArrayList<Integer>();
			
			try {	
				
				String sql = "SELECT * FROM patient_disease WHERE patient_id LIKE ?"; 			
				PreparedStatement prep = DBManagerSQL.c.prepareStatement(sql);	
				ResultSet rs = prep.executeQuery();
			
			while (rs.next()) { 
				int disease_id = rs.getInt("drug_id");
				thePatientDiseasesById.add(disease_id);
		}
			
			prep.close();
			rs.close();
			
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			return thePatientDiseasesById;
		}
		
		
		
		public List<DiseasePojo> listDiseasesByName(List<Integer> theDiseasesByName ) { //Buscamos la medicina que es según el id q le pasemos del paciente
			
			List<DiseasePojo> thePatientDiseasesByName = new ArrayList<DiseasePojo>();
			
			try {	
				String sql = "SELECT * FROM Disease WHERE id LIKE ?"; 			
				PreparedStatement prep = DBManagerSQL.c.prepareStatement(sql);	
				ResultSet rs = prep.executeQuery();
			
			while (rs.next()) { 
				String name = rs.getString("name");
				DiseasePojo disease = new DiseasePojo(name);
				thePatientDiseasesByName.add(disease);
				
		}
			
			prep.close();
			rs.close();
			
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			return thePatientDiseasesByName;
		}



	}//class