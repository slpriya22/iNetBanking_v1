package com.iNetBanking_v1.testCases;

//work on this later for hashmap..anyway to get from hashmap we need 2d array to transfer from it to hashmap
//
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.iNetBanking_v1.pageObjects.LoginPage;
import com.iNetBanking_v1.utilities.XLUtils;

//try to store username and password in a hashmap from excel sheet
//if it is invalid credentials then test case is failed with given custom message to be sent to report

public class TC_LoginDDT_002_myCode extends BaseClass_myCode {
	// using page object class for login page like in other LoginPageTest class
	// same name from data provider method and here small d for dataProvider
	// to do valid and invalid test cases.refer notes for test case scenario
	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException // it takes user n password from data
																				// provider method
	{

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);// pass the user from data provider method
		logger.info("Entered username");
		lp.setPassword(pwd);
		logger.info("Entered password");
		Thread.sleep(5000);
		lp.clickLogin();
		logger.info("Login button clicked");

		// to check whether alert is present or not-user defined method
		if (isAlertPresent() == true) // if 1 failed login case
		{
			String alertBoxMsg = acceptAlert();
			Thread.sleep(2000);
			if (alertBoxMsg != null)// if 2 starts-------------------------
			{
				if (alertBoxMsg.equalsIgnoreCase("User or Password is not valid")) // if 3 start----------------
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
		String pagetitle = driver.getTitle();
		if (pagetitle != null) // if 1 starts
		{
			// can compare with any unique sentence of the page also
			if (pagetitle.equalsIgnoreCase("Guru99 Bank Manager HomePage")) // if 2 starts
			{
				logger.info("Login successful and page title verification passed");
				Assert.assertTrue(true);
				lp.clickLogout();// after one set of input login,click logout and accept the alert box
				logger.info("Clicked Logout link to go back to login page for next credentials validation.");
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
		}

	}

	// see comments below for more understanding
	@DataProvider(name = "LoginData") // note CapsD in Dataprovider
	String[][] getData() throws IOException // return type is 2d string array

	{
		String xlFilePath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\iNetBanking_v1\\testData\\LoginData.xlsx";
		// String xlFilePath = System.getProperty("user.dir")
		// + "/src/test/java/com/iNetBanking_v1/testData/LoginData.xlsx";// try with /
		// and also with \\

		int rowcount = XLUtils.getRowCount(xlFilePath, "Sheet1");

		int colcount = XLUtils.getCellCount(xlFilePath, "Sheet1", 1);

		String logindata[][] = new String[rowcount][colcount];

		for (int i = 1; i <= rowcount; i++)// i-1 will be stored in the 2d array,when i =0,v should assign -1 to the 2d
											// array i value
		{
			for (int j = 0; j < colcount; j++)// same j is used in the 2d array
			{

				logindata[i - 1][j] = XLUtils.getCellData(xlFilePath, "Sheet1", i, j);// [0][0]=[1][0] n it increments
			}

		}
		return logindata;
	}
}

//	learn and work more..modify the get cell data method also...to transfer from hashmap
//write a test example in java and then implement here
// @DataProvider(name = "LoginData")
// HashMap<String, String> getDataMethod() {

// }

// HashMap<String, String> hm = new HashMap<String, String>();

// String xlFilePath = System.getProperty("user.dir")
// + "\\src\\test\\java\\com\\iNetBanking_v1\\testData\\LoginData.xlsx";
// int rowcount = XLUtils.getRowCount(xlFilePath, "Sheet1");

// int colcount = XLUtils.getCellCount(xlFilePath, "Sheet1", 1);

// String logindata[][] = new String[rowcount][colcount];

// i-1 will be stored in the 2d array,when i =0,v should assign -1 to the 2d
// array i value
// for(
// int i = 1;i<=rowcount;i++)
// {
// for (int j = 0; j < colcount; j++)// same j is used in the 2d array
// {
// logindata[i - 1][j] = XLUtils.getCellData(xlFilePath, "Sheet1", i, j);
// } // for j end
// }// for i end
//	return hm;
//}// getDataMethod
//}// class ends

// create data provider method with name"LoginData".use same name in the main
// testcase
// get rowcount,col count,use for loop get the values and store in the 2d array
// read data from excel and store it in 2d array
// with logic from xl [1][0] and [1][1] for username and password to
// [0][0] and [0][1] so from [x][y] from xl file to [x-1][y] to 2d array
// sheet name is Sheet1,can rename anything too in the excel file and update
// here
// get the number of column from any row,so mentioned as row 1 as number of cols
// is same for all the rows
