package com.iNetBanking_v1.testCases;

//data driven test case.add this test case in testng xml

//this has data provider method and the test case
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.iNetBanking_v1.pageObjects.LoginPage;
import com.iNetBanking_v1.utilities.XLUtils;

//more detailed conditions are added in my code
//this test case uses data driven method to get many usernames and passwords from the excel file
//see the notes n comments below here for more explanation
public class TC_LoginDDT_002_Training extends BaseClass_Training {

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
		if (isAlertPresent() == true) {
			Thread.sleep(3000);
			driver.switchTo().alert().accept();// if alert is present present ok and close it for both invalid n logout
												// actions
			// so no need to check the alert text and all unless some specific action needs
			// to be taken
			Thread.sleep(3000);
			driver.switchTo().defaultContent();// it goes back to main page
			// no need for this ,it automatically comes to main page
			Assert.assertTrue(false);// this is failure case
			logger.warn("Login failed");

		} else {
			Assert.assertTrue(true);// if alert not present then my case is passed
			logger.info("Login passed");
			Thread.sleep(3000);
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();// close logout alert
			Thread.sleep(3000);
			driver.switchTo().defaultContent();// better to use it incase i doesnt go automatically
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
}
