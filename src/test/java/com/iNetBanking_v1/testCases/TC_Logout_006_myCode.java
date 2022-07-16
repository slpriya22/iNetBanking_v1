package com.iNetBanking_v1.testCases;

//chk the else part of the pagetitle!=null...even when if is executed,it is stille xcuting else part
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.iNetBanking_v1.pageObjects.LoginPage;

public class TC_Logout_006_myCode extends BaseClass_myCode {

	@Test
	public void logoutTest() throws InterruptedException, IOException

	{// method starts

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);// passing from baseclass
		logger.info("Entered username");
		lp.setPassword(password);
		logger.info("Entered password");
		Thread.sleep(5000);
		lp.clickLogin();
		logger.info("Login button clicked");
		if (isAlertPresent() == true) // if1 failed login case
		{
			String alertBoxMsg = acceptAlert();
			Thread.sleep(2000);
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

		// if 1 end---------------------
		logger.info("Login successful");
		String pagetitle = driver.getTitle();// pass case
		// String pagetitle = null;//for fail case
		if (pagetitle != null) {
			lp.clickLogout();// after one set of input login,click logout and accept the alert box

			String alertBoxMsg = acceptAlert();
			Thread.sleep(2000);
			if (alertBoxMsg.equalsIgnoreCase("You Have Succesfully Logged Out!!")) // if 3 starts
			{
				logger.info("Logged out successfully");
				Assert.assertTrue(true);

				// Assert.assertTrue(true);
			} // if3 end --------------------------
			else // ----------------------------------------------
			{
				logger.warn(
						"Alert box log out message not matching the expected message.Please Check report and log for more details and try again.");
				Assert.assertTrue(false, "No or wrong alert message.");
			} // else1 end
		} // if 2 ends
		else {
			logger.warn(
					"Page is missing the title where logout link is clicked. Please check log and reports and try again.");
			Assert.assertTrue(false, "Page is missing the title where logout link is clicked.");
		}
	}
}
