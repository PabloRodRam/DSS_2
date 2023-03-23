package db.menu;

import java.io.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

import db.interfaces.UserManager;
import db.jdbc.DBManagerSQL;
import db.menu.Utils;
import db.pojos.users.User;



public class Menu {
	/*

	private static UserManager userMan = new DBManagerSQL();
	
	
	//public static void main(String[] args) throws Exception, IOException {
	
		do {
		//LOGGING IN
		System.out.println("Choose an option: ");
		System.out.println("1. Register ");
		System.out.println("2. Login ");
		System.out.println("3. Change my email address ");
		System.out.println("0. Exit the program ");
		int choice = Utils.readInt();
		
        switch (choice) {
            case 1:
            	register();
                break;
                
            case 2: 
            	login();
            	break;
            	
            case 3:
            	changeEmail();
            	break;
            	
            case 0:
            	//dbMan.disconnect();
            	userMan.disconnect();
            	System.exit(0);
            	break;
            	
            default:
                System.out.println("Error, nonvalid input.");
                break;
        }
    
	}while (true);
	
	
		/*private static void register() throws IOException, Exception {
		System.out.println("Please type in your email address:");
		String email = Utils.readLine();
		
		Boolean bol = KeyboardInput.adminMan.checkForUsers(email);*/
		
		/*do {
			if(bol == true) {
				System.out.println("This email is already used. Please pick another one: ");
				email = Utils.readLine();
				
				if(KeyboardInput.adminMan.checkForUsers(email) == false) {
					bol = false;
				}
			}
		}while (bol == true);
		
		System.out.println("Now write your password:");
		String password = Utils.readLine();
		
		System.out.println(dbMan.get);
		System.out.println("Type the chosen role ID: ");
		int id = Utils.readInt();
		Role role = userMan.getRole(id);
		
		MessageDigest md = MessageDigest.getInstance("MD5"); //for changing the password into a different language which is MD5
		md.update(password.getBytes());
		byte[] hash = md.digest(); //the hash that is the one that translates
		
		User user = new User (email, hash, role);
		userMan.newUser(user);*/
	//}
	
/*
	private static void register() throws IOException, Exception {
		System.out.println("Please type in your email address:");
		String email = Utils.readLine();
		//Comprobamos si realmente el email existe
		
		System.out.println("Now write your password:");
		String password = Utils.readLine();
		
		MessageDigest md = MessageDigest.getInstance("MD5"); //for changing the password into a different language which is MD5
		md.update(password.getBytes());
		byte[] hash = md.digest(); 
		
		User user = new User ();
		userMan.newUser(user);
	}

	private static void login() throws IOException, Exception{
	
		System.out.println("Please type in your email address:");
		String email = Utils.readLine();
		
		System.out.println("Now write your password:");
		String password = Utils.readLine();
		
		User user = userMan.checkPassword(email, password); //we do not need a hash bc of the method we are using
		
		if (user == null) {
			System.out.println("Wrong email of password");
		}	
	}//login
	
	
	//hola?
	
	
	private static void changeEmail() throws IOException, Exception{
		System.out.println("Please type in your email address:");
		String emailOld = Utils.readLine();
		System.out.println("Now write your password:");
		String password = Utils.readLine();
		User user = userMan.checkPassword(emailOld, password);
		
		if (user == null) {
			System.out.println("Wrong email of password");
		} else {
			System.out.println("Now write your new email: ");
			String emailNew = Utils.readLine();
			
			//WE CHECK FOR THE USER TO EXIST WITH A BOOLEAN
			
			Boolean bol = true;
			
			do {
				/*if(bol == true) {
					System.out.println("This email is already used. Please pick another one: ");
					emailNew = Utils.readLine();
					
					if(KeyboardInput.adminMan.checkForUsers(emailNew) == false) {
						bol = false;
					}
				}
			}while (bol == true);
			
			userMan.updateEmail(emailNew, emailOld);*/
			/*System.out.println("Your email has been changed.");
			
			}while (bol == true);
		}//else
	}//changeEmail?? LO QUEREMOS ESTO????
*/

}//menu


