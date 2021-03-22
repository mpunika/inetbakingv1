package com.inetbanking.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.LoginPage;

public class TC_LoginTest_001 extends TestBase{
	
	@Test
	public void testPageTitleValidation() throws Exception {
		driver.get(url);
		logger.info("Url is entered");
		Thread.sleep(5000);
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		lp.setPassword(passWord);
		lp.buttonClick();
		logger.info("UserName and Password entered");
		String actualTitle = driver.getTitle();
		System.out.println("The AcutalTitle is "+actualTitle);
		System.out.println("The ExpectedTitle is "+expectedTitle);
		logger.info("Actual Title and Expected Title is compared");
		
		if(actualTitle.equals(expectedTitle)) {
		   
			Assert.assertTrue(true);
			logger.info("Test Passed  :Actual Title and Expected Title are equal");
		}else {
			captureScreen(driver,"testPageTitleValidation");
			Assert.assertTrue(false);
			logger.info("Test Failed  :Actual Title and Expected Title are not equal");
		}
	}

}
