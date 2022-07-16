package com.iNetBanking_v1.testCases;

//no reset button is used
//no alert check when invalid username n password entered..
//this one uses reporting_training
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.iNetBanking_v1.pageObjects.LoginPage;

public class TC_LoginPageTest_001_Training extends BaseClass_Training {

	@Test
	public void loginTest() throws InterruptedException, IOException {

		logger.info("URL is opened");

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered username");

		lp.setPassword(password);
		logger.info("Entered password");

		lp.clickLogin();// in pavan training used clickSubmit()

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login test passed");
		} else {
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");// this wont work after the assertion is failed.use this before assert stt
		}

	}
}
