package com.inetbanking.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver ldriver;
	
	public LoginPage(WebDriver driver) {
		ldriver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name ="uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name ="password")
	@CacheLookup
	WebElement txtPassWord;
	
	@FindBy(name ="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	public void setUserName(String userName) {
		txtUserName.sendKeys("mngr314579");
	}
	
	public void setPassword(String passWord) {
		txtPassWord.sendKeys("udEmymy");
	}
	
	public void buttonClick() {
		btnLogin.click();
	}

}
