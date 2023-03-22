package db.interfaces;

import db.pojos.users.User;

import java.util.List;

import db.pojos.*;

public interface DiseaseManager {
	

	public void addDisease(DiseasePojo disease);
	public List<DiseasePojo> listDiseases();


	//funciones de los diseases
	
	//syptoms de la disease
	//descripcion del disease
	
}
