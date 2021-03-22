package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	
	public ReadConfig() {
		File propfile = new File("./configuration/config.properties");
		
		try {
			
			FileInputStream fis = new FileInputStream(propfile);
			prop = new Properties();
			prop.load(fis);
			
		}catch(IOException e) {
			System.out.println("The message is "+e.getMessage());
		}

	}
	
	public String getBrowser() {
		String browser = prop.getProperty("browser");
		return browser;
	}
	public String getUrl() {
		String url = prop.getProperty("url");
		return url;
	}
	public String getUserName() {
		String userName = prop.getProperty("username");
		return userName;
	}
	public String getPassword() {
		String passWord = prop.getProperty("password");
		return passWord;
	}
	public String getExpectedTitle() {
		String expectedTitle = prop.getProperty("expectedtitle");
		return expectedTitle;
	}
	public String getExpectedTitle1() {
		String expectedTitle1 = prop.getProperty("expectedtitle1");
		return expectedTitle1;
	}
	

}
