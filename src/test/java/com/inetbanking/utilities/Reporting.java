package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testContext) {
		
		//Time Stamp
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		//ReportName
		String reportName = "Test-Report-"+timeStamp+".html";
		
		//Location of the Report
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+
				            "/test-output/"+reportName); 
		
		//Load the extent-config.xml file
		try {
		sparkReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		}catch(IOException e) {
			System.out.println("The message is "+e.getMessage());
		}
		extent = new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Enviroment", "QA");
		extent.setSystemInfo("user", "Amingo");
		
		//Title of the Report
		sparkReporter.config().setDocumentTitle("InetBanking V6");
		//Name of Report
		sparkReporter.config().setReportName("InetBanking Functional Test Report");
		//Set the Theme
		sparkReporter.config().setTheme(Theme.STANDARD);	
				
	}

    public void onTestSuccess(ITestResult tr) {
    	
    	//Create new entry in report
    	logger = extent.createTest(tr.getName());
    	
    	//Send the passed information
    	logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN ));
    }
    
    public void onTestFailure(ITestResult tr) {
    	
    	//Create new entry in report
    	logger = extent.createTest(tr.getName());
    	
    	//Send the failure information
    	logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED ));
    	
    	String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
    	
    	File file = new File(screenshotPath);
    	
    	if(file.exists()) {
    		logger.fail("Screenshot is below: "+ logger.addScreenCaptureFromPath(screenshotPath));
    	}
    }
    
    public void onTestSkipped(ITestResult tr) {
    	
    	//Create new entry in report
    	logger = extent.createTest(tr.getName());
    	
    	//Send the failure information
    	logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.YELLOW));
    	
  }
  
    public void onFinish(ITestContext testContext) {
    	
    	extent.flush();
    	
  }

}
