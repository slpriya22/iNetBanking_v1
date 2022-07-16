package com.iNetBanking_v1.testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.iNetBanking_v1.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass_Training {
	/*
	 * public String baseURL = "https://demo.guru99.com/V4/"; public String username
	 * = "mngr424110";// in real scenarios lots of username and password has to be
	 * checked public String password = "tutYqEp";// validity of username n password
	 * for 20 days only from 11th july 2022
	 */
	// In real time project ,we cannot hardcode values in the code, it has to be
	// sent from xl or from config.properties file.Any changes to be done in config
	// file

	ReadConfig readconfig = new ReadConfig();// import readconfig class as it is in another package
	public String baseURL = readconfig.getApplicationURL();// as it returns string store it in string variable
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();

	public static WebDriver driver;// position of this is not important
	// public WebDriver driver;//use this for reporting_screenshot
	public static Logger logger;// same like webdriver,this one also declared
	// globally

	// no import junit annotation.only testNG one

	// before every test cases not the methods.Here before each .java test case
	// import parameters fromtestng annotations not from any other class
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

	// use this with pavan training reporting class..this method is called from the
	// test case failure case else cndn stts..try executing it
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\Screenshots\\" + tname + timeStamp + ".png");
		// use and also check and confirm what to be
		// used for path
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	// these random methods are used here testing purposes only
	public static String randomNum(int count) // see notes for thos
	{
		String randomNumString = RandomStringUtils.randomNumeric(count);// 10 is the count
		return (randomNumString);
	}

	public String randomString(int lengthOfChars) {
		String randomStr = RandomStringUtils.randomAlphabetic(lengthOfChars);
		return randomStr;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();// if alert is not present it throws exception
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}

}
