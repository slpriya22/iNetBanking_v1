package com.iNetBanking_v1.utilities;

//use this code with screenshot user defined method in this class itself
//THIS code only working as per expectation
//CHECK NOTES WHAT TO CHANGE TO USE THIS..
// AFTER THIS CLASS,EXECUTION IS DONE ONLY THRU TESTNG.xml
//Listener class used to create extent reports
//after writing the listener class,integrate it with the test cases in the testNG.xml using listener tag
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;no longer supported
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.iNetBanking_v1.testCases.BaseClass_myCode;

//this program uses extent report,listeners for pass,fail and skip

public class Reporting_myCode extends TestListenerAdapter {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testcontext) {

		String timeStamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
//java has simpleDateformat

		String reportName = "Test-Report-" + timeStamp + ".html";// generated report name with timestamp
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + reportName);
		// location with report name

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "local");
		extent.setSystemInfo("user", "slp");

		sparkReporter.config().setDocumentTitle("iNetBanking Test Project");// title of the report
		sparkReporter.config().setReportName("Functional Test Report");// name of the report
		// sparkReporter.config().setTheme(Theme.STANDARD);// background will be
		// standard
		sparkReporter.config().setTheme(Theme.DARK);// background of report will be
		// dark
		// sparkReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		// google for this error n does sparkreporter class supor this or what is the
		// replacement
		// chartlocation is no longer supported
		// for any custom settings add the extent-config.xml and
		// extent.loadConfig(new File(System.getProperty("user.dir") +
		// "\\extent-config.xml"));// connecting xml file here
		// add the extent-config.xml to the path mentioned along with log4j,pom.xml
		// NOT SUPPORTING IN NEWER VERSION loadConfig or loadXmlConfig

		/* WITH THIS EXTENT REPORT SETUP IS DONE OnTestStart */
	}

	public void onTestSuccess(ITestResult testresult)
	// on failure it marks with red and capture screnshot of failure and add it to
	// the report
	{
		BaseClass_myCode.logger.info("Report generated for passed test.Check report for details");
		logger = extent.createTest(testresult.getName());// creating new entry in the report with name of the
															// method/error
		logger.log(Status.PASS, MarkupHelper.createLabel(testresult.getName(), ExtentColor.GREEN));
		// send the passed information to the report with GREEN color highlighted
	}

	/*
	 * public void onTestFailure(ITestResult testresult) { logger =
	 * extent.createTest(testresult.getName());// creating new entry in the report
	 * with name of the // method/error logger.log(Status.FAIL,
	 * MarkupHelper.createLabel(testresult.getName(), ExtentColor.RED)); // send the
	 * failed information to the report with RED color highlighted
	 * 
	 * // have to take screenshot of the failed test String screenshotpath =
	 * System.getProperty("user.dir") + "/Screenshots/" + testresult.getName() +
	 * "png"; File f = new File(screenshotpath); if (f.exists()) {
	 * logger.fail("Screenshot is below:" +
	 * logger.addScreenCaptureFromPath(screenshotpath)); } // if end
	 * 
	 * // try catch block for IOException ..it is not compatible with listener
	 * 
	 * }
	 */

	public void onTestFailure(ITestResult testresult) {
		BaseClass_myCode.logger.info("Test Failed- report updated with screenshot.Please check report for details");
		logger = extent.createTest(testresult.getName());// creating new entry in the report with name of the
															// method/error
		logger.log(Status.FAIL, MarkupHelper.createLabel(testresult.getName(), ExtentColor.RED));
		// send the failed information to the report with RED color highlighted
		// getName() gets the failed test case method name and report will be generated
		// with that name
		logger.log(Status.FAIL,
				MarkupHelper.createLabel(testresult.getThrowable() + " - Test Case Failed", ExtentColor.RED));

		// have to take screenshot of the failed test
		String screenshotpath = null;
		try {
			screenshotpath = getScreenshot(BaseClass_myCode.driver, testresult.getName());// if webdriver is declared
			// static
			// screenshotpath = getScreenshot(bc.driver, testresult.getName());//if
			// webdriver is not declred static
		} // -----------------
		catch (IOException e) // ---------------------
		{
			e.printStackTrace();
		}
		// String screenshotpath = System.getProperty("user.dir") + "/Screenshots/" +
		// testresult.getName() + "png";
		File f = new File(screenshotpath);
		if (f.exists()) {
			logger.fail("Screenshot is above:" + logger.addScreenCaptureFromPath(screenshotpath));
		} // if end

	}

	public void onTestSkipped(ITestResult testresult) {
		BaseClass_myCode.logger.info("Test Skipped-report updated with screenshot.Please check report for details");
		logger = extent.createTest(testresult.getName());// creating new entry in the report with name of the
															// method/error
		logger.log(Status.SKIP, MarkupHelper.createLabel(testresult.getName(), ExtentColor.ORANGE));
		// send the skipped information to the report with ORANGE color highlighted
		logger.log(Status.SKIP,
				MarkupHelper.createLabel(testresult.getThrowable() + " - Test Case Skipped", ExtentColor.ORANGE));
		String screenshotpath = null;
		try {
			screenshotpath = getScreenshot(BaseClass_myCode.driver, testresult.getName());
		} // -----------------------
		catch (IOException e) // ---------------------
		{
			e.printStackTrace();
		}
		File f = new File(screenshotpath);
		if (f.exists()) {
			logger.fail("Screenshot is above:" + logger.addScreenCaptureFromPath(screenshotpath));
		} // if end

	}

	public void onFinish(ITestContext textcontext) {
		extent.flush();
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		// different reports are created with datetime stamp instead of old report
		// getting replaced
		// because with addition of date time stamp file names are different
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		System.out.println("Screenshot taken.");
		return destination;
	}

}
