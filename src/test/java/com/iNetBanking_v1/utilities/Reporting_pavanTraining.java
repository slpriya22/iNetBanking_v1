package com.iNetBanking_v1.utilities;
//This code will work and the user defined method for screenshot is written in base class

// AFTER THIS CLASS,EXECUTION IS DONE ONLY THRU TESTNG.xml
//Listener class used to create extent reports
//after writing the listener class,integrate it with the test cases in the testNG.xml using listener tag
import java.io.File;
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
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;no longer supported
import com.aventstack.extentreports.reporter.configuration.Theme;

//this program uses extent report,listeners for pass,fail and skip

public class Reporting_pavanTraining extends TestListenerAdapter {

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
		sparkReporter.config().setTheme(Theme.STANDARD);// background will be standard
		// sparkReporter.config().setTheme(Theme.DARK);//background of report will be
		// dark
		// sparkReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		// google for this error n does sparkreporter class supor this or what is the
		// replacement
		// chartlocation is no longer supported
		// for any custom settings add the EXTENT-CONFIG.XML
		// extent.loadXmlConfig(new File(System.getProperty("user.dir") +
		// "\\extent-config.xml"));// connecting xml file
		// here

		/* WITH THIS EXTENT REPORT SETUP IS DONE OnTestStart */
	}

	public void onTestSuccess(ITestResult testresult)
	// on failure it marks with red and capture screnshot of failure and add it to
	// the report
	{
		logger = extent.createTest(testresult.getName());// creating new entry in the report with name of the
															// method/error
		logger.log(Status.PASS, MarkupHelper.createLabel(testresult.getName(), ExtentColor.GREEN));
		// send the passed information to the report with GREEN color highlighted
	}

	public void onTestFailure(ITestResult testresult) {
		logger = extent.createTest(testresult.getName());
		// creating new entry in the report with name of the // method/error
		logger.log(Status.FAIL, MarkupHelper.createLabel(testresult.getName(), ExtentColor.RED));
		logger.log(Status.FAIL,
				MarkupHelper.createLabel(testresult.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		// send the failed information to the report with RED color highlighted

		// it attaches the screenshot from already taken by user defined method in
		// baseclass..getting the path os screenshot here thatsall
		String screenshotpath = System.getProperty("user.dir") + "/Screenshots/" + testresult.getName() + "png";
		File f = new File(screenshotpath);
		if (f.exists())// -----
		{
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotpath));
		} // if end

		// try catch block for IOException ..it is not compatible with listener

	}

	public void onTestSkipped(ITestResult testresult) {
		logger = extent.createTest(testresult.getName());// creating new entry in the report with name of the
															// method/error
		logger.log(Status.SKIP, MarkupHelper.createLabel(testresult.getName(), ExtentColor.ORANGE));
		logger.log(Status.SKIP,
				MarkupHelper.createLabel(testresult.getThrowable() + " - Test Case skipped", ExtentColor.ORANGE));
		// send the skipped information to the report with ORANGE color highlighted
		// in this approach screenshot of skipped test not taken..have to call
		// separately the sccreenshot of method and attaches again here
		// as skipped steps are not known from the test cases sometimes.even before
		// driver opens skip step may occur
	}

	public void onFinish(ITestContext textcontext) {
		extent.flush();
	}

}
