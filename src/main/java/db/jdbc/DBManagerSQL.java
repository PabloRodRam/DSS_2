package db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.*;
import db.pojos.DiseasePojo;
import db.pojos.DrugPojo;
import db.pojos.Gender;
import db.pojos.PatientPojo;
import db.pojos.SymptomsPojo;
import db.pojos.users.User;

public class DBManagerSQL implements AirCheckMan{

	public static Connection c;

	public void connect() {
		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./AirCheck.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");

			if (c != null) {
				System.out.print("Conected succesfully\n");
			} else {
				System.out.print("Conection unsuccesful\n");
			}

			this.createTables();

		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
			System.out.print("SQL exception occured\n");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("An exception has occured\n");

		}
	}

	
	public void disconnect() {

		try {
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void createTables() throws SQLException {

		try {

			String[] tableNames = { "Patient", "Disease", "Symptoms", "Drugs", "patient_disease", "patient_drug",
					"symptom_drug", "symptom_disease", "patient_symptom" };
			DatabaseMetaData metadata = c.getMetaData();

			for (String tableName : tableNames) {
				ResultSet resultSet = metadata.getTables(null, null, tableName, null);

				if (!resultSet.next()) {
					System.out.println("Creating tables...");

					Statement stmt1 = c.createStatement();
					String sql1 = "CREATE TABLE Patient " + "(id		INTEGER		PRIMARY		KEY	AUTOINCREMENT,"
							+ " name		TEXT		NOT NULL," 
							+ " gender 	ENUM		NOT NULL," 
							+ " age		INTEGER		NOT NULL,"
							+ " userId	INTEGER		REFERENCES users(id))";

					stmt1.executeUpdate(sql1);
					stmt1.close();

					Statement stmt2 = c.createStatement();
					String sql2 = "CREATE TABLE Disease " + "(id			INTEGER		PRIMARY		KEY	AUTOINCREMENT,"
							+ " name			TEXT		NOT NULL," + " basicInfo	TEXT		NOT NULL,"
							+ " link			TEXT		NOT NULL," 
							+ " scoreMax		INTEGER)";

					stmt2.executeUpdate(sql2);
					stmt2.close();

					Statement stmt3 = c.createStatement();
					String sql3 = "CREATE TABLE Symptoms " + "(id			INTEGER		PRIMARY		KEY	AUTOINCREMENT,"
							+ " name			TEXT		NOT NULL)";

					stmt3.executeUpdate(sql3);
					stmt3.close();

					Statement stmt4 = c.createStatement();
					String sql4 = "CREATE TABLE Drugs " + "(id			INTEGER		PRIMARY		KEY	AUTOINCREMENT,"
							+ " name			TEXT		NOT NULL)";

					stmt4.executeUpdate(sql4);
					stmt4.close();

					Statement stmt5 = c.createStatement();
					String sql5 = "CREATE TABLE patient_disease " + "(patient_id	INTEGER	REFERENCES Patient(id), "
							+ " disease_id	INTEGER	REFERENCES Disease(id), "
							+ " PRIMARY KEY (patient_id, disease_id) )";
					stmt5.executeUpdate(sql5);
					stmt5.close();

					Statement stmt6 = c.createStatement();
					String sql6 = "CREATE TABLE patient_drug " + "(patient_id	INTEGER	REFERENCES Patient(id), "
							+ " drug_id	INTEGER	REFERENCES Drugs(id), " + " PRIMARY KEY (patient_id, drug_id) )";
					stmt6.executeUpdate(sql6);
					stmt6.close();

					Statement stmt7 = c.createStatement();
					String sql7 = "CREATE TABLE symptom_drug " + "(symptom_id	INTEGER	REFERENCES Symptoms(id), "
							+ " drug_id	INTEGER	REFERENCES Drugs(id), " + " PRIMARY KEY (symptom_id, drug_id) )";
					stmt7.executeUpdate(sql7);
					stmt7.close();

					Statement stmt8 = c.createStatement();
					String sql8 = "CREATE TABLE symptom_disease " + "(symptom_id	INTEGER	REFERENCES Symptoms(id), "
							+ " disease_id	INTEGER	REFERENCES Disease(id), "
							+ " PRIMARY KEY (symptom_id, disease_id) )";
					stmt8.executeUpdate(sql8);
					stmt8.close();

					Statement stmt9 = c.createStatement();
					String sql9 = "CREATE TABLE patient_symptom " + "(patient_id	INTEGER	REFERENCES Patient(id), "
							+ "symptom_id	INTEGER	REFERENCES Symptoms(id), "
							+ " PRIMARY KEY (patient_id, symptom_id) )";

					stmt9.executeUpdate(sql9);
					stmt9.close();

					System.out.println("Now the tables exists.");

				} else {
					System.out.println("Table " + tableName + " exists.");
				}
			} 

		} catch (SQLException e) {
			if (!e.getMessage().contains("already exists" + "\n")) {
				e.printStackTrace();
			}
		} 

	}
	


	public void addDisease(DiseasePojo disease) {
		
		try {

			String sql = "INSERT INTO Disease ( name, basicInfo, link, scoreMax)";
			sql+= " VALUES (?,?,?,?)";
			PreparedStatement pstmt = DBManagerSQL.c.prepareStatement(sql); 
			
			//pstmt.setInt(1, disease.getId());
			pstmt.setString(1,disease.getName().toString());
			pstmt.setString(2,disease.getBasicInfo()); //Como es null no me deja meterlo
			pstmt.setString(3,disease.getLink());
			pstmt.setFloat(4,disease.getScore_max());
			
			
			pstmt.executeUpdate();
			pstmt.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}//addPDisease
	

	
	
	public List<DiseasePojo> listDiseases(){
			
		List<DiseasePojo> theDiseases = new ArrayList<DiseasePojo>();
		
		try {	
			
			String sql = "SELECT * FROM Diseases "; 			
			PreparedStatement prep = DBManagerSQL.c.prepareStatement(sql);	
			ResultSet rs = prep.executeQuery();
		
		while (rs.next()) { 
			int id = rs.getInt("id");	
			String name = rs.getString("name");
			String basicInfo = rs.getString("basicInfo"); 
			String link = rs.getString("link"); 
			Float scoreMax = rs.getFloat("scoreMax");
			

			DiseasePojo undisease = new DiseasePojo(id, name, basicInfo, link, scoreMax);
			theDiseases.add(undisease);
			
			}

		prep.close();
		rs.close();
		
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return theDiseases;
		
	}//listDiseases
	

	public void addDrug(DrugPojo drug) {
		try {

			String sql = "INSERT INTO Drugs ( name)";
			sql+= " VALUES (?)";
			PreparedStatement pstmt = DBManagerSQL.c.prepareStatement(sql); 
			
			//pstmt.setInt(1, drug.getId());
			pstmt.setString(1,drug.getName().toString());
			
			
			
			pstmt.executeUpdate();
			pstmt.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}//addDrug
	
	
	
	public List<DrugPojo> listDrugs(){
		
		List<DrugPojo> theDrugs = new ArrayList<DrugPojo>();
		
		try {	
					
				String sql = "SELECT * FROM Drugs "; 			
				PreparedStatement prep = DBManagerSQL.c.prepareStatement(sql);	
				ResultSet rs = prep.executeQuery();
				
			while (rs.next()) { 
				int id = rs.getInt("id");	
				String name = rs.getString("name");
		
				DrugPojo unDrug = new DrugPojo(id, name);
				
				theDrugs.add(unDrug);  //no entiendo
					
				}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return theDrugs	;
		}//listSymptoms




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

		public void addSymptom(SymptomsPojo symptom) {
			
			
			try {

				String sql = "INSERT INTO Symptoms (name)";
				sql+= " VALUES (?)";
				PreparedStatement pstmt = DBManagerSQL.c.prepareStatement(sql); 
				
				//pstmt.setInt(1, symptom.getId());
				pstmt.setString(1,symptom.getName().toString());
				
				
				pstmt.executeUpdate();
				pstmt.close();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		}//addPatient
		
		
		public List<SymptomsPojo> listSymptoms() { //we show all the animals in the database
			List<SymptomsPojo> theSymptoms = new ArrayList<SymptomsPojo>();
			
			try {	
				
				String sql = "SELECT * FROM Symptoms "; 			
				PreparedStatement prep = DBManagerSQL.c.prepareStatement(sql);	
				ResultSet rs = prep.executeQuery();
			
			while (rs.next()) { 
				int id = rs.getInt("id");	
				String name = rs.getString("name");
				
				SymptomsPojo unSymptom = new SymptomsPojo(id, name);
				
				theSymptoms.add(unSymptom); //no entiendo esto
				
				
				}

			prep.close();
			rs.close();
			
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			return theSymptoms	;
		}//listSymptoms
		
	
	
}
