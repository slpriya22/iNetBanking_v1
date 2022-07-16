package com.iNetBanking_v1.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
//call login page test from here to login and add registrstion failure by checking alert box n acceting n send assert false to send screenshot
import org.testng.Assert;
import org.testng.annotations.Test;

import com.iNetBanking_v1.pageObjects.AddNewCustomerPage;
import com.iNetBanking_v1.pageObjects.LoginPage;

public class TC_AddNewCustomerPageTest_003_myCode extends BaseClass_myCode {

	@Test(priority = 2)
	public void addNewCustomer() throws InterruptedException {

		logger.info("Login into url started");
		// need to do from login itself..in real time call login page test
		LoginPage lp = new LoginPage(driver);// this driver comes from baseclass

		lp.setUserName(username);
		logger.info("Entered username");

		lp.setPassword(password);
		logger.info("Entered password");

		Thread.sleep(2000);

		lp.clickLogin();
		logger.info("Clicked login button");// verification of login and all added in loginPageTest..can call that class
											// while executing as a whole

		logger.info("Login is successful");

		logger.info("-----Adding new customer details------");

		AddNewCustomerPage newcust = new AddNewCustomerPage(driver);

		newcust.clickNewCustomerLink();
		logger.info("Clicked new customer link from home page");
		Thread.sleep(1000);
		driver.navigate().refresh();

		Thread.sleep(3000);
		// handling stale element referencce exception
		try {
			newcust.clickNewCustomerLink();
			logger.info("Closed some random ad by refreshing home page");
		} catch (StaleElementReferenceException e) {
			// again identify the element and click it when the exception throws
			logger.info("Closed some random ad by refreshing home page");
			newcust = new AddNewCustomerPage(driver);// the element is located again and checking
			newcust.clickNewCustomerLink();
			logger.info("Again new customer link is clicked after refreshing");

		}

		Thread.sleep(2000);
		newcust.clickReset();
		logger.info("Reset button is clicked to clear the fields before entering new customer details");

		// newcust.setCustomerName("Arvindan");
		newcust.setCustomerName(customername);
		logger.info("Entered customer name");
		Thread.sleep(1000);
		// newcust.clickGender("male");
		newcust.clickGender(gender);
		logger.info("Entered gender");
		Thread.sleep(1000);
		// newcust.setDob("15/05/1990");
		newcust.setDob(dob);
		logger.info("Entered date of birth");
		Thread.sleep(1000);
		// newcust.setAddress("10 beach road ghost town");// special characters are not
		// allowed
		newcust.setAddress(address);
		logger.info("Entered address");
		Thread.sleep(1000);
		// newcust.setCity("Jersey city");
		newcust.setCity(city);
		logger.info("Entered city");
		Thread.sleep(1000);
		// newcust.setState("NewJersey");
		newcust.setState(state);
		logger.info("Entered state");
		Thread.sleep(1000);
		int pinCodeLength = 6;// see notes..pin must have 6 digits
		String pincode = randomNum(pinCodeLength);// only when pin code is completely numbers.
		// if it is alphabets then hardcode or send from config file..just for testing
		// purpose used it.In real time config file or excel is used to send large
		// number of datas
		newcust.setPin(pincode);
		logger.info("Entered pin");
		Thread.sleep(1000);
		int mobileNumLength = 10;
		String mobileno = randomNum(mobileNumLength);
		newcust.setMobileNum(mobileno);// randomNum returns string of random numeric characters
		logger.info("Entered mobile number");
		Thread.sleep(1000);
		// unique email id has to be passed

		int emailLength = 5;
		String emailtype = "@gmail.com";// can use any email type @yahoo.com,@xxx.com
		String email = randomString(emailLength) + emailtype;
		newcust.setEmailId(email);
		logger.info("Entered email id");
		Thread.sleep(1000);
		int passwordLength = 7;
		String password = randomAlphaNum(passwordLength);
		newcust.setPassword(password);
		logger.info("Entered password");

		Thread.sleep(3000);

		newcust.clickSubmit();
		logger.info("Clicked submit button");

		// validate the registration
		logger.info("----Validating registration process----");

		// alert box appears if customer cannot be added due to some problem
		// stating'customer cannot be added'

		if (isAlertPresent() == true) {// else if starts

			String alertBoxMsg = acceptAlert();
			Thread.sleep(2000);
			if (alertBoxMsg != null) {// if 1 starts
				if (alertBoxMsg.contains("please fill all fields")
						|| alertBoxMsg.contains("Customer could not be added !!")) {// if 2 starts
					logger.info(
							"Registration failed due to incorrect or incomplete details.Please check log and report and try again.");
					Assert.assertTrue(false, "Registration failed due to incorrector incomplete details.");// this is
																											// failure
																											// case
				} // if 2 ends-----------------
				else // -----------------------
				{
					logger.warn(
							"Error alert box message not matching the expected message.Please Check report and log for more details and try again.");
					// Assert.assertTrue(false, "Alert error message not matching with expected
					// message.");
					Assert.assertEquals(alertBoxMsg, "'please fill all fields' or 'Customer could not be added !!'");
				}

			} // if 1 ends---------
			else {
				logger.warn(" Alert box not present or with no message.Please check log and report for more details.");
				// captureScreen(driver,"addNewCustomer");//when using pavan reporting class
				Assert.assertTrue(false, "Alert box not present or with no message.");

			} // end else

		} // end else if
		else {
			// In real times,real datas are used
			boolean regSuccess = driver.getPageSource().contains("Customer Registered Successfully!!!");
			// registration is success when message matches with expected success message
			if (regSuccess == true) {
				// remove the String id and logging info delete it..just to use for delete test
				// case i m getting it
				String id = driver.findElement(By.xpath("//*[@id=\"customer\"]/tbody/tr[4]/td[2]")).getText();
				logger.info("customer details created with id: " + id);
				Assert.assertTrue(true);
				logger.info("New customer details registered successfully and validation is successful.");
			} // if end-------
		}
	}// method end
}// class end
