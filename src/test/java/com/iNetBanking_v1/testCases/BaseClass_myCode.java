package com.iNetBanking_v1.testCases;
//changes to do for screenshot-remove static from webdriver driver ..make the login test fail in test case

//whatever methods used commonly like capture screen,randomnum,random string in baseclass which as common for these test cases
//add customer details also in config file and try accessing form baseclass

//utilities class is different .It has supported methods. Like report generation and all.It dont have annotations.
//In base class we use annotation.without baseclass also test case can be run.These set up can be done in individual test cases also
//username and password is valid only for 20 days from 11th july 2022.
//need driver setup,logging,

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.logging.log4j.core.Logger;//no no
//import org.apache.logging.log4j.Logger;//no no
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.iNetBanking_v1.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass_myCode {

	// In real time project ,we cannot hardcode values in the code, it has to be
	// sent from xl or from config.properties file.
	// Any changes to be done in config file
	// public String baseURL = "https://demo.guru99.com/V4/";
	// public String username = "mngr424110";// in real scenarios lots of username
	// and password has to be checked
	// public String password = "tutYqEp";// validity of username n password for 20
	// days only from 11th july 2022

	ReadConfig readconfig = new ReadConfig();// import readconfig class as it is in another package
	public String baseURL = readconfig.getApplicationURL();// as it returns string store it in string variable
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public String customername = readconfig.getCustomerName();
	public String gender = readconfig.getGender();
	public String dob = readconfig.getDOB();
	public String address = readconfig.getAddress();
	public String city = readconfig.getCity();
	public String state = readconfig.getState();
	public String customerid = readconfig.getCustId();

	public static WebDriver driver;// position of this is not important

	public static Logger logger;// same like webdriver,this one also declared
	// globally

	// no import junit annotation.only testNG one
	// this @BeforeClass before every test cases not the methods.Here before each
	// .java test case
	// import parameters from testng annotations not from any other class

	@BeforeClass
	@Parameters("browser") // it returns the value and that is passed in the setUp method as string
	public void setUp(String browser) throws IOException {

		// setting log
		logger = Logger.getLogger("iNetBanking");// or can give the class name for more clarity
		PropertyConfigurator.configure("log4j.properties");// import from org.apache.log4j
		// add dependency from log4j-1.2-api not any other dependency like in the
		// commented import above..see notes for which dependency to be used or this

		if (browser.equalsIgnoreCase("chrome")) // -----------------
		{
			logger.info("Launching chrome browser");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("Chrome browser launched succesfully");
		} // -------------------------------
		else if (browser.equalsIgnoreCase("firefox")) // ----------------------
		{
			logger.info("Launching firefox browser");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.info("Firefox browser launched succesfully");
		} // ------------------------
		else if (browser.equalsIgnoreCase("ie")) // ---------------------------
		{
			logger.info("Launching InternetExplorer(IE) browser");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			logger.info("InternetExplorer(IE) launched succesfully");
		}
		/*
		 * add for other browsers if needed WebDriverManager.edgedriver().setup();
		 * WebDriverManager.operadriver().setup(); WebDriverManager.phantomjs().setup();
		 */
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// adding log message wherever applicable
		logger.info("Opened url: " + baseURL);// logger.debug or logger.warning also can be used based on requirement
	}

	// after every test cases not the methods.Here after each .java test case
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	// these random methods are used here testing purposes only
	public static String randomNum(int count) // see notes for thos
	{
		String randomNumString = RandomStringUtils.randomNumeric(count);// 10 is the count
		return (randomNumString);
	}

	public static String randomAlphaNum(int count) {
		String randomAlphaNumStr = RandomStringUtils.randomAlphanumeric(count);
		return randomAlphaNumStr;

	}

	public String randomString(int lengthOfChars) {
		String randomStr = RandomStringUtils.randomAlphabetic(lengthOfChars);
		return randomStr;
	}

	// user defined method created to check whether alert is present or not
	// can put in uitlities or in baseclass
	public boolean isAlertPresent() throws InterruptedException {
		try {
			Thread.sleep(2000);
			driver.switchTo().alert();// if alert is not present it throws exception
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}

	public String acceptAlert() throws InterruptedException {
		String alertTextMsg = driver.switchTo().alert().getText();

		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		return alertTextMsg;
	}

	// if want to use separately
	// public static void getScreenshot(WebDriver driver, String screenshotName)
	// throws IOException {
	// unfortunately screenshot of alert box is not possible
	// String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	// different reports are created with datetime stamp instead of old report
	// getting replaced
	// TakesScreenshot ts = (TakesScreenshot) driver;
	// File source = ts.getScreenshotAs(OutputType.FILE);

	// after execution, you could see a folder "FailedTestsScreenshots" under src
	// folder
	// String destination = System.getProperty("user.dir") + "\\Screenshots\\" +
	// screenshotName + dateName + ".png";
	// String destination = System.getProperty("user.dir") + "\\Screenshots\\" +
	// screenshotName + ".png";
	// File finalDestination = new File(destination);
	// FileUtils.copyFile(source, finalDestination);
	// System.out.println("Screenshot taken");
	// return destination;
	// }

}
