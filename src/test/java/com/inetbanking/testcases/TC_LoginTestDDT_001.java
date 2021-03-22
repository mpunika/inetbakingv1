package com.inetbanking.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.LoginPage;
import com.inetbanking.utilities.XLSUtils;

public class TC_LoginTestDDT_001 extends TestBase {

	@Test(dataProvider="LoginData")
	public void loginDDT(String userName , String passWord) throws Exception {
		driver.get(url);
		logger.info("Url is entered");
		Thread.sleep(5000);
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		System.out.println(userName);
		lp.setPassword(passWord);
		System.out.println(passWord);
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
    
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException{
	
	String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testdata/LoginTestData.xlsx";
	int rownum = XLSUtils.getRowCount(path, "Sheet1");
	int cellcount = XLSUtils.getCellCount(path,"Sheet1", 1);
	
	String logindata[][] = new String[rownum][cellcount];
	
	for(int i = 1;i<=rownum -1;i++) {
		
		for(int j = 0;j <= cellcount -1 ;j++ ) {
			
			logindata[i-1][j] = XLSUtils.getCellData(path,"Sheet1",i,j) ;
			
		}
	}
	   return logindata;
		
	}
	
	
}
