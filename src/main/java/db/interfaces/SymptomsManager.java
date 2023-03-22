package db.interfaces;

import java.util.List;

import db.pojos.*;

public interface SymptomsManager {
	public void addSymptom(SymptomsPojo symptom);
	public List<SymptomsPojo> listSymptoms();
}
