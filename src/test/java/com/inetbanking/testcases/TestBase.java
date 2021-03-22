package com.inetbanking.testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Logger logger;
	
	public static ReadConfig config = new ReadConfig();
	
	//public String browser = config.getBrowser();
	public String url = config.getUrl();
	public String userName = config.getUserName();
	public String passWord = config.getPassword();
	public String expectedTitle = config.getExpectedTitle();
	public String expectedTitle1 = config.getExpectedTitle1();
	
	public void LaunchBrowser(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
		
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("Edge")) {
			
		    WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}else {
			
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		
	}
	public void captureScreen(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot is taken");
	}
	@Parameters("browser")
	
	@BeforeTest
	public void testSetUp(String browser) throws Exception {
		
		logger = Logger.getLogger("InetBanking");
		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("Test started");
		LaunchBrowser(browser);
		logger.info("Browser Launched");
	
		
	}
	
	@AfterTest
	public void testTearDown() throws Exception {
		Thread.sleep(10000);
		driver.quit();
		logger.info("Test is Completed");
		
	}

}
