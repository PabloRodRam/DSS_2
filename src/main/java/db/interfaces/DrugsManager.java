package db.interfaces;
import java.util.List;

import db.pojos.*;

public interface DrugsManager {
	public void addDrug(DrugPojo drug);
	public List<DrugPojo> listDrugs();
}
