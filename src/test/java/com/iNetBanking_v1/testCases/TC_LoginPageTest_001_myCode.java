//add steps to verify failed login too..chk the alert box for failure
//unfortunately screenshot of alert cannot be taken
//add steps to logout also ...so for each test case login can be done separately

//all testcases.java should extend baseclass
//Test case should contain only @Test methods..any common should be in baseclass
//see notes for errors encountered and its solution
package com.iNetBanking_v1.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;//don't import junit only testNG

import com.iNetBanking_v1.pageObjects.LoginPage;

//Baseclass common methods will get executed with @BeforeClass
public class TC_LoginPageTest_001_myCode extends BaseClass_myCode {// class starts

	@Test(priority = 1)
	public void loginTest() throws InterruptedException, IOException

	{// method starts

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);// passing from baseclass
		logger.info("Entered username");
		lp.setPassword(password);
		logger.info("Entered password");
		Thread.sleep(5000);
		lp.clickLogin();
		logger.info("Login button clicked");

		// to check whether alert is present or not-user defined method
		if (isAlertPresent() == true) // if1 failed login case
		{
			String alertBoxMsg = acceptAlert();
			Thread.sleep(2000);
			if (alertBoxMsg != null)// if2 starts-------------------------
			{
				if (alertBoxMsg.equalsIgnoreCase("User or Password is not valid")) // if3 start----------------
				{
					logger.warn("ERROR MESSAGE from Alert window is: " + alertBoxMsg);
					logger.info("Screenshot of alert box is not possible!!!");
					logger.info("Please check reports and try again.");
					Assert.assertTrue(false, "Login failed due to invalid username or password.");
				} // if3-----------------------
				else // -----------------------
				{
					logger.warn(
							"Error alert box present with no message or not matching the expected message.Please Check report and log for more details and try again.");
					Assert.assertTrue(false, "No or wrong alert error message.");
				} // else 1 end
			} // if 2 end

		} // if 1 end---------------------
		String pagetitle = driver.getTitle();// positive case
		// String pagetitle = "something";// for failing page title comparison
		// String pagetitle = null;// another failure case
		if (pagetitle != null) // if 1 starts
		{
			// can compare with any unique sentence of the page also
			if (pagetitle.equalsIgnoreCase("Guru99 Bank Manager HomePage")) // if 2 starts
			{
				logger.info("Login successful and page title verification passed");
				Assert.assertTrue(true);
				lp.clickLogout();// after one set of input login,click logout and accept the alert box
				logger.info("Clicked Logout.");
				Thread.sleep(3000);
				String alertBoxMsg = acceptAlert();
				Thread.sleep(3000);
				if (alertBoxMsg.equalsIgnoreCase("You Have Succesfully Logged Out!!")) // if 3 starts
				{
					logger.info("Logged out successfully");
				} // if3 end --------------------------
				else // ----------------------------------------------
				{
					logger.warn(
							"Alert box log out message not matching the expected message.Please Check report and log for more details and try again.");
					Assert.assertTrue(false, "No or wrong alert message.");
				} // else1 end
			} // if 2 ends

			else {
				logger.info("Login is done but page title verification failed. Please check the reports.");
				logger.warn(
						"Actual and expected home page titles after login are not matching.Please compare both titles");
				Assert.assertEquals(pagetitle, "Guru99 Bank Manager HomePage",
						"Actual and expected home page titles after login are not matching");
			} // else if ends

		} // if 1 ends
		else {
			logger.warn("Page not opened or page title is missing. Please check the log and report for more details.");
			Assert.assertTrue(false,
					"Page not opened or page title is missing.Something wrong. Please verify and try again");
		} // else ends

	}// method ends
}// class ends
