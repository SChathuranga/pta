package testbase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import helper.browserconfiguration.BrowserType;
import helper.browserconfiguration.ChromeBrowser;
import helper.browserconfiguration.FirefoxBrowser;
import helper.browserconfiguration.config.ObjectReader;
import helper.browserconfiguration.config.PropertyReader;
import helper.logger.LoggerHelper;
import helper.resource.ResourceHelper;
import helper.wait.WaitHelper;
import utils.ExtentManager;

public class TestBase {
	
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectory;
	
	
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();
	}
	
	@BeforeTest
	public void beforeTest() throws Exception {
		// reader is nothing but the reference of the interface  
		ObjectReader.reader = new PropertyReader();
		reportDirectory = new File(ResourceHelper.getResourcePath("/resources/screenshots"));
		setUpDriver(ObjectReader.reader.getBrowserType());
	}
	
	@AfterTest
	public void afterTest() throws Exception{
		if(driver!=null) {
			driver.quit();
		}
	}
	
	@BeforeClass
	public void beforeClass() {
		// test will get initialized with the class name
		test =  extent.createTest(getClass().getSimpleName());
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName() + " test-started-testbase");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			//capture screenshot and add it in the extent report
			//capture screen method will return the location of the captured image 
			String imagePath = captureScreen(result.getName(), driver);
			test.addScreenCaptureFromPath(imagePath);
		} 
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " test-passed-testbase");
			//capture screenshot and add it in the extent report
			//capture screen method will return the location of the captured image 
			String imagePath = captureScreen(result.getName(), driver);
			test.addScreenCaptureFromPath(imagePath);
		} 
		else if(result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}
		// to create the HTML file
		extent.flush();
	}
	
	
	public WebDriver getBrowserObject(BrowserType browsertype) throws Exception {
		try {
			switch(browsertype) {
			case Chrome: 
				// get object of Chrome browser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions chromeoptions = chrome.getChromeOptions();
				return chrome.getChromeDriver(chromeoptions);
				// break not required since we return 
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions firefoxoptions = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(firefoxoptions);
				// break not required since we return 
			default:
				throw new Exception("Driver not found" + browsertype.name());
			}
		}
		catch(Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	// this method will setup the driver, launch the browser and wait 
	public void setUpDriver(BrowserType browsertype) throws Exception {
		driver = getBrowserObject(browsertype);
	
		log.info("Initialize WebDriver " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}
	
	
	// https://www.youtube.com/watch?v=3Om2PR8VJT4&list=PL5NG-eEzvTthT8eMv6PIgDI7SHL_ajvnE&index=29
	// https://www.youtube.com/watch?v=E8DSisTo5Yg&list=PL5NG-eEzvTthT8eMv6PIgDI7SHL_ajvnE&index=34
	// we pass a WebDriver instance as the argument because, if we are calling from some other class you will have to pass the driver object because the driver wont be available at that time
	public String captureScreen(String fileName, WebDriver driver) {
		if(driver == null) {
			log.info("driver is null");
			return null;
		}
		if(fileName == "") {
			fileName = "blank";
		}
		 
		File destFile = null;
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		// This will capture the screenshot in the runtime
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			destFile = new File(reportDirectory + "/" + fileName + "_" + formatter.format(calender.getTime()) + ".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			// testng log  
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath() + "'height='100' width='100'/></a>");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}
	
	// method will capture screenshots and add them in the emailable report 
	public void captureScreenShot(WebDriver driver) {
		log.info("capturing screenshot");
		// captureScreen method will take a screenshot and add the link to the emailable report
		String screen  = captureScreen("", driver);
		// this will add the screenshot to the extent report
		try {
			test.addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void logExtentReport(String message) {
		test.log(Status.INFO, message);
	}
	
	public void getApplicationURL(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		log.info("Navigating to : " + url);
		logExtentReport("Navigating to : " + url);
	}
	
	
	
}
