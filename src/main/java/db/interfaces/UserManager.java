package db.interfaces;

import db.pojos.users.User;

//import db.pojos.users.User;

public interface UserManager {
	public void connect();
	public void disconnect();
	
	public void newUser(User u);
	public User checkPassword(String email, String password);
	public Boolean checkEmail(String email);
	public void deleteUser(String mail, String password);
	public void updateUserMail(String newMail, String oldMail, String password);
	public void updateUserPassword(String mail, String newPassword, String oldPassword);
	void updateUserMailWithoutpass(String newMail, String oldMail);
	boolean updateUserPassword(String mail, String newPassword, String oldPassword, boolean ca);
}
