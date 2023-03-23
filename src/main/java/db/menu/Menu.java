package db.menu;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

import db.interfaces.*;
import db.jdbc.*;
import db.jpa.*;
import db.menu.Utils;
import db.pojos.users.User;

public class Menu {

	private static UserManager userMan = new JPAUserManagment();
	private static AirCheckMan inter = new DBManagerSQL();

	public static void main(String[] args) throws Exception, IOException {
		inter.connect();
		userMan.connect();

		do {
			// LOGGING IN
			System.out.println("Choose an option: ");
			System.out.println("1. Register ");
			System.out.println("2. Login ");
			System.out.println("0. Exit the program ");
			int choice = Utils.readInt();

			switch (choice) {
			case 1:
				register();
				break;

			case 2:
				login();
				break;

			case 0:
				inter.disconnect();
				userMan.disconnect();
				System.exit(0);
				break;

			default:
				System.out.println("Error, nonvalid input.");
				break;
			}

		} while (true);

	}

	private static void register() throws IOException, Exception {
		System.out.println("Please type in your email address:\n");
		String email = Utils.readLine();

		if (userMan.checkEmail(email)) {
			System.out.println("Email already used, try to log in\n");
		} else {
			System.out.println("Now, please, introduce your password:\n");
			String password = Utils.readLine();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] hash = md.digest();
			User u = new User(email, hash);
			userMan.newUser(u);
		}

	}

	private static void login() throws IOException, Exception {

		System.out.println("Please type in your email address:");
		String email = Utils.readLine();

		System.out.println("Now write your password:");
		String password = Utils.readLine();

		User user = userMan.checkPassword(email, password); // we do not need a hash bc of the method we are using

		if (user == null) {
			System.out.println("Wrong email of password");
		}

		continueMenu();
	}

	private static void continueMenu() throws Exception, NumberFormatException, IOException {
		do {
			System.out.println("Choose an option: ");
			System.out.println("1. Diagnose ");
			System.out.println("2. Delete account ");
			System.out.println("3. Change my email address ");
			System.out.println("0. Exit the program ");
			int choice = Utils.readInt();

			switch (choice) {
			case 1:
				diagnoseMenu();
				break;

			case 2:
				deleteAccount();
				break;

			case 3:
				changeEmail();
				break;

			case 0:
				inter.disconnect();
				userMan.disconnect();
				System.exit(0);
				break;

			default:
				System.out.println("Error, nonvalid input.");
				break;
			}

		} while (true);

	}

	private static void diagnoseMenu() throws Exception, NumberFormatException, IOException {
		
		do {
			System.out.println("Choose an option: ");
			System.out.println("1. Select deseases ");
			System.out.println("2. Select medication ");
			System.out.println("3. Submit and diagnose ");
			System.out.println("0. Go Back ");
			int choice = Utils.readInt();

			switch (choice) {
			case 1:
				symptomMenu();
				break;

			case 2:
				medsMenu();
				break;

			case 3:
				confirmMenu();
				break;

			case 0:
				continueMenu();
				break;

			default:
				System.out.println("Error, nonvalid input.");
				break;
			}

		} while (true);

	}

	private static void confirmMenu() throws Exception{
		// TODO Auto-generated method stub
		
	}

	private static void medsMenu() throws Exception{
		do {
			System.out.println("Choose an option: ");
            System.out.println("1. Beta-Blockers");
            System.out.println("2. NSAIDs");
            System.out.println("3. Opioids");
            System.out.println("4. Statins");
            System.out.println("5. Bronchodilators");
            System.out.println("6. Stimulants");
            System.out.println("7. Antidepressants");
            System.out.println("8. ACE inhibitors");
            System.out.println("9. Antipsychotics");
            System.out.println("10. Chemotherapy drugs");
            System.out.println("11. Antibiotics");
            System.out.println("12. Antihistamines");
            System.out.println("13. SSRIs");
			System.out.println("0. Go Back ");
			
			int choice = Utils.readInt();

			switch (choice) {
			case 1:
                System.out.println("Beta-Blockers");
                break;
            case 2:
                System.out.println("NSAIDs");
                break;
            case 3:
                System.out.println("Opioids");
                break;
            case 4:
                System.out.println("Statins");
                break;
            case 5:
                System.out.println("Bronchodilators");
                break;
            case 6:
                System.out.println("Stimulants");
                break;
            case 7:
                System.out.println("Antidepressants");
                break;
            case 8:
                System.out.println("ACE inhibitors");
                break;
            case 9:
                System.out.println("Antipsychotics");
                break;
            case 10:
                System.out.println("Chemotherapy drugs");
                break;
            case 11:
                System.out.println("Antibiotics");
                break;
            case 12:
                System.out.println("Antihistamines");
                break;
            case 13:
                System.out.println("SSRIs");
                break;
            case 0:
            	diagnoseMenu();
            	break;

			default:
				System.out.println("Error, nonvalid input.");
				break;
			}
			
		}while(true);
		
	}
		
	

	private static void symptomMenu() throws Exception {
		do {
			System.out.println("Choose an option: ");
	        System.out.println("1. Shortness of breath or difficulty breathing");
	        System.out.println("2. Rapid breathing or breathing too fast");
	        System.out.println("3. Wheezing");
	        System.out.println("4. Cyanosis");
	        System.out.println("5. Headache");
	        System.out.println("6. Confusion/Disorientation");
	        System.out.println("7. Irregular heartbeat");
	        System.out.println("8. Chest pain");
	        System.out.println("9. Fatigue/Weakness");
	        System.out.println("10. Dizziness");
	        System.out.println("11. Fainting or loss of consciousness");
	        System.out.println("12. Excessive daytime sleepiness");
	        System.out.println("13. Difficulty concentrating");
	        System.out.println("14. Irritability");
	        System.out.println("15. Depression");
	        System.out.println("16. Anxiety");
	        System.out.println("17. Restless sleep");
	        System.out.println("18. Insomnia");
	        System.out.println("19. Dry mouth or sore throat");
	        System.out.println("20. Chest oppression or/and discomfort");
	        System.out.println("21. Muscle or body pain");
	        System.out.println("22. Cough");
	        System.out.println("23. Cough with blood");
	        System.out.println("24. Cough that gets worse over time");
	        System.out.println("25. Difficulty performing physical activities");
	        System.out.println("26. Loss of appetite or weight loss");
	        System.out.println("27. Hoarseness");
	        System.out.println("28. Trouble swallowing");
	        System.out.println("29. Fever or chills");
	        System.out.println("30. Loss of taste or smell");
	        System.out.println("31. Congestion or running nose");
	        System.out.println("32. Nausea or vomiting");
	        System.out.println("33. Inflammation of the respiratory tract");
	        System.out.println("34. Sweating");
	        System.out.println("35. Mucus");
	        System.out.println("36. Expectoration");
	        System.out.println("37. Stridor");
	        System.out.println("38. Tachycardia");
	        System.out.println("39. Hypertemia");
	        System.out.println("40. Loss of negative pressure");
	        System.out.println("41. Sneezing");
	        System.out.println("42. Chronic cough");
			System.out.println("0. Go Back ");
			
			int choice = Utils.readInt();

			switch (choice) {
			case 1:
                System.out.println("Shortness of breath or difficulty breathing");
                break;
            case 2:
                System.out.println("Rapid breathing or breathing too fast");
                break;
            case 3:
                System.out.println("Wheezing");
                break;
            case 4:
                System.out.println("Cyanosis");
                break;
            case 5:
                System.out.println("Headache");
                break;
            case 6:
                System.out.println("Confusion/Disorientation");
                break;
            case 7:
                System.out.println("Irregular heartbeat");
                break;
            case 8:
                System.out.println("Chest pain");
                break;
            case 9:
                System.out.println("Fatigue/Weakness");
                break;
            case 10:
                System.out.println("Dizziness");
                break;
            case 11:
                System.out.println("Fainting or loss of consciousness");
                break;
            case 12:
                System.out.println("Excessive daytime sleepiness");
                break;
            case 13:
            	System.out.println("Difficulty concentrating");
                break;
            case 14:
                System.out.println("Irritability");
                break;
            case 15:
                System.out.println("Depression");
                break;
            case 16:
                System.out.println("Anxiety");
                break;
            case 17:
                System.out.println("Restless sleep");
                break;
            case 18:
                System.out.println("Insomnia");
                break;
            case 19:
                System.out.println("Dry mouth or sore throat");
                break;
            case 20:
                System.out.println("Chest oppression or/and discomfort");
                break;
            case 21:
                System.out.println("Muscle or body pain");
                break;
            case 22:
                System.out.println("Cough");
                break;
            case 23:
                System.out.println("Cough with blood");
                break;
            case 24:
                System.out.println("Cough that gets worse over time");
                break;
            case 25:
                System.out.println("Difficulty performing physical activities");
                break;
            case 26:
                System.out.println("Loss of appetite or weight loss");
                break;
            case 27:
                System.out.println("Hoarseness");
                break;
            case 28:
                System.out.println("Trouble swallowing");
                break;
            case 29:
                System.out.println("Fever or chills");
                break;
            case 30:
                System.out.println("Loss of taste or smell");
                break;
            case 31:
                System.out.println("Congestion or running nose");
                break;
            case 32:
                System.out.println("Nausea or vomiting");
                break;
            case 33:
                System.out.println("Inflammation of the respiratory tract");
                break;
            case 34:
                System.out.println("Sweating");
                break;
            case 35:
                System.out.println("Mucus");
                break;
            case 36:
                System.out.println("Expectoration");
                break;
            case 37:
                System.out.println("Stridor");
                break;
            case 38:
                System.out.println("Tachycardia");
                break;
            case 39:
                System.out.println("Hypertemia");
                break;
            case 40:
                System.out.println("Loss of negative pressure");
                break;
            case 41:
                System.out.println("Sneezing");
                break;
            case 42:
                System.out.println("Chronic cough");
                break;

			default:
				System.out.println("Error, nonvalid input.");
				break;
			}
			
		}while(true);
		
	}

	private static void deleteAccount() throws Exception {
		System.out.println("Please, introduce again your email address:");
		String email = Utils.readLine();
		System.out.println("Now, please, introduce again your password:");
		String password = Utils.readLine();
		System.out.println("Are you sure you want to delete your account? (YES / NO)");
		String sure = Utils.readLine();
		if (sure.equalsIgnoreCase("yes")) {
			userMan.deleteUser(email, password);
		}

	}

	private static void changeEmail() throws IOException, Exception {
		System.out.println("Please, introduce again your email address:");
		String oldEmail = Utils.readLine();
		System.out.println("Now, please, introduce your new email address:");
		String newEmail = Utils.readLine();
		System.out.println("Now, please, introduce again your password:");
		String password = Utils.readLine();
		System.out.println("Are you sure you want to change your email address? (YES / NO)");
		String sure = Utils.readLine();
		if (sure.equalsIgnoreCase("yes")) {
			userMan.updateUserMail(newEmail, oldEmail, password);
		}
	}// changeEmail

}// menu
