package com.iNetBanking_v1.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

//ReadConfig don't extend BaseClass so cant use logger.If want to use logger extend BaseClass
//we are using webdriver manager for browser driver initiate.so no need for path from config.properties..just browser name is enough
public class ReadConfig {

	Properties pro;

	// whatever class obj declare it globally in any class
	public ReadConfig() {
		File src = new File("./Configuration\\config.properties");// if not working change to / and try
		// when copied the path from file->rightclick->properties->resource it
		// automatically changes to \\ from /
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
		/* PROPERTY FILE IS LOADED TILL NOW. TO READ EACH VALUE WE USE METHODS */
	}

	public String getApplicationURL() // this method will be called from the BaseClass to utitlise the value returned
										// by this method
	{
		String url = pro.getProperty("baseURL");// getProperty is used to get the property from the class specified
		// System.getProperty means it gets property from the system
		return url;
	}

	public String getUsername() {
		String username = pro.getProperty("username");// case sensitive from config.properties
		return username;
	}

	public String getPassword() {
		String password = pro.getProperty("password");
		return password;
	}

	public String getCustomerName() {
		String customername = pro.getProperty("customername");
		return customername;
	}

	public String getGender() {
		String gender = pro.getProperty("gender");
		return gender;
	}

	public String getDOB() {
		String dob = pro.getProperty("dob");
		return dob;
	}

	public String getAddress() {
		String address = pro.getProperty("address");
		return address;
	}

	public String getCity() {
		String city = pro.getProperty("city");
		return city;
	}

	public String getState() {
		String state = pro.getProperty("state");
		return state;
	}

	public String getCustId() {
		String custid = pro.getProperty("custid");
		return custid;
	}

}
