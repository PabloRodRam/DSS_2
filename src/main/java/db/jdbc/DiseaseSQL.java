package db.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.DiseaseManager;
import db.pojos.DiseasePojo;
import db.pojos.PatientPojo;

public class DiseaseSQL implements DiseaseManager{
	



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
}
