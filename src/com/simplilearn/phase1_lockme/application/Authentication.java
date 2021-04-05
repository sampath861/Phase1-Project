package com.simplilearn.phase1_lockme.application;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.simplilearn.Phase1_lockme.model.*;



public class Authentication {
	static Scanner scanner;
	static Scanner dbinput;
	static Scanner lockerinput;
	static PrintWriter lockerOutput;
	static PrintWriter dbOutput;
	static Users_db users;
	static UserCredentials_lockerdb usercredentials;
	static File dbFile;
	static File lockerFile;

	public static void main(String[] args) throws IOException {
		
	startApplication();
	welcomeScreen();
	signInOptions();

	}

	
	public static void signInOptions() throws IOException {
		System.out.println("1 . Registration ");
		System.out.println("2 . Login ");
		 scanner=new Scanner(System.in);
		System.out.println("Please enter the option");
		int option=scanner.nextInt();
		switch(option) {
			case 1 : 
				registerUser();
				break;
			case 2 :
				loginUser();
				break;
			default :
				System.out.println("Please select 1 Or 2");
				break;
		}
		
	}

	


	public static void registerUser() {
		
		System.out.println("==========================================");
		System.out.println("*   WELCOME TO REGISTRATION PAGE	*");
		System.out.println("==========================================");
		
		System.out.println("Enter Username :");
		String username = scanner.next();
		
		
		System.out.println("Enter Password :");
		String password = scanner.next();
	
		Users_db users=new Users_db(username,password);
		
		users.setUsername(username);
		users.setPassword(password);
		
		dbOutput.println(users.getUsername());
		dbOutput.println(users.getPassword());
		dbOutput.close();
		
		
	    System.out.println("User Registration Suscessful");

}
	
	public static void loginUser() throws IOException  {

		System.out.println("==========================================");
		System.out.println("*   WELCOME TO LOGIN PAGE	 *");
		System.out.println("==========================================");
		System.out.println("Enter Username :");
		String inputUsername = scanner.next();
		boolean flag = false;
		while(dbinput.hasNext() && !flag) {
			if(dbinput.next().equals(inputUsername)) {
				System.out.println("Enter Password :");
				String inputPassword = scanner.next();
				if(dbinput.next().equals(inputPassword)) {
					System.out.println("Login Successful ! 200OK");
					flag = true;
					lockerOptions(inputUsername);
					break;
				}
			}
		}
		if(!flag) {
			System.out.println("User Not Found");
		}
		
	}
	
	
	public static void lockerOptions(String inputUsername) throws IOException {
		System.out.println("1 . FETCH ALL STORED CREDENTIALS ");
		System.out.println("2 . STORED CREDENTIALS ");
		System.out.println("Enter option :");
		int option = scanner.nextInt();
		switch(option) {
			case 1 : 
				fetchCredentials(inputUsername);
				break;
			case 2 :
				storeCredentials(inputUsername);
				break;
			case 3 :
				deleteCredentials(inputUsername);
				break;
			default :
				System.out.println("Please select 1 Or 2");
				break;
		}
		
	}

	private static void storeCredentials(String signinuser) {
		
		System.out.println("==========================================");
		System.out.println("*   WELCOME TO DIGITAL LOCKER STORE YOUR CREDS HERE	 *");
		System.out.println("==========================================");
		
		usercredentials.setSigninuser(signinuser);
		
		System.out.println("Enter Site Name :");
		String siteName = scanner.next();
		usercredentials.setSitename(siteName);
		
		System.out.println("Enter Username :");
		String username = scanner.next();
		usercredentials.setUsername(username);
		
		System.out.println("Enter Password :");
		String password =scanner.next();
		usercredentials.setPassword(password);
		
		lockerOutput.println(usercredentials.getSigninuser());
		lockerOutput.println(usercredentials.getSitename());
		lockerOutput.println(usercredentials.getUsername());
		lockerOutput.println(usercredentials.getPassword());
		
		System.out.println("YOUR CREDENTIALS ARE STORED AND SECURED!");
		lockerOutput.close();
		
		
		
	}


	private static void fetchCredentials(String inputUsername) {
		System.out.println("==========================================");
		System.out.println("*   WELCOME TO DIGITAL LOCKER 	 *");
		System.out.println("==========================================");
		System.out.println(inputUsername);
		
		
		while(lockerinput.hasNext()) {
			if(lockerinput.next().equals(inputUsername)) {
				System.out.println("Site Name: "+lockerinput.next());
				System.out.println("User Name: "+lockerinput.next());
				System.out.println("User Password: "+lockerinput.next());
			}
		}
		
	}
	
	private static void deleteCredentials(String inputUsername) throws IOException {
		System.out.println("==========================================");
		System.out.println("*   WELCOME TO DIGITAL LOCKER 	 *");
		System.out.println("==========================================");
		
		String data = null;
		try {
			data = readFileAsString("lockerfile.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    String[] arraydata=data.split("\n");
	    List<String> list=new ArrayList<String>();
	    
	    for(int i=0;i<arraydata.length;i++)
	    {
	    	list.add(arraydata[i]);
	    }
	    for(int i=0;i<=list.size();i++)
	    {
	    	if(list.size()==0)
	    	{
	    		System.out.println("empty file");
	    	}
	    	else
	    	{
	    	if( list.get(i).equals(inputUsername))
	    	{
	    		System.out.println("Following Site Name: "+list.remove(i)+"    removed");
				System.out.println("Following User Name: "+list.remove(i+1)+"  removed");
				System.out.println("Following User Password:  "+list.remove(i+2)+"  removed");
				
				FileWriter writer = null;
				try {
					writer=new FileWriter(lockerFile,false);
				} catch (IOException e) {
					
					e.printStackTrace();
				} 
				for(String str: list) {
					
						writer.write(str);
					
					
				}
				writer.close();
				
				
				
				break;
	    	}
	    	
	    }
	    }

	   
		
		
	}
	
	
	
	
	

	
	public static void startApplication(){

		
		try {
			
			dbFile = new File("Database.txt");
			lockerFile = new File("lockerfile.txt");
			
			//read data from db file
			dbinput = new Scanner(dbFile);
			
			//Read data from locker file
			lockerinput = new Scanner(lockerFile);
			
			//Read data from keyboard
			scanner = new Scanner(System.in);
			
			users = new Users_db();
			usercredentials  = new UserCredentials_lockerdb();
			
			//out put 
				dbOutput =new PrintWriter( new FileWriter(dbFile,true));
				lockerOutput = new PrintWriter( new FileWriter(lockerFile,true));
				
				
			
	} catch (IOException e) {
		System.out.println("File Not Found ");
	}
			
		
	}
	
	public static void welcomeScreen() {
		System.out.println("==========================================");
		System.out.println("*   Welcome To LockMe.com		*");
		System.out.println("*   Your Personal Digital Locaker	*");
		System.out.println("==========================================");
		
	}
	
	private static String readFileAsString(String fileName)throws Exception
	  {
	    String data = "";
	    data = new String(Files.readAllBytes(Paths.get(fileName)));
	    return data;
	  }

}

