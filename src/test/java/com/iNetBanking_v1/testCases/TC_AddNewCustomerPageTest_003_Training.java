package com.iNetBanking_v1.testCases;

import java.io.IOException;

//after clicking new customer link some random ad page appears..this code doesnt handle that...
//my code handles the random ad page by refreshing the page,but results in stale element exception due to refresh
//My code handles stale element execption also
import org.testng.Assert;
import org.testng.annotations.Test;

import com.iNetBanking_v1.pageObjects.AddNewCustomerPage;
import com.iNetBanking_v1.pageObjects.LoginPage;

public class TC_AddNewCustomerPageTest_003_Training extends BaseClass_Training {
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {

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

		newcust.setCustomerName("Arvindan");
		logger.info("Entered customer name");

		newcust.clickGender("male");
		logger.info("Entered gender");

		newcust.setDob1("10", "2", "1999");// this sends the values separately,but not entering year value porperly in
											// the dob field
		// my code directly sends as mm/dd/yyyy as single string value
		logger.info("Entered date of birth");

		Thread.sleep(3000);// it takes some time to enter all the dob values

		newcust.setAddress("10 beach road ghost town");// special characters are not allowed
		logger.info("Entered address");

		newcust.setCity("Jersey city");
		logger.info("Entered city");

		newcust.setState("NewJersey");
		logger.info("Entered state");

		newcust.setPin("654327");
		logger.info("Entered pin");

		newcust.setMobileNum("9876543210");
		logger.info("Entered mobile number");

		// unique email id has to be passed

		int emailLength = 5;
		String emailtype = "@gmail.com";// can use any email type @yahoo.com,@xxx.com
		String email = randomString(emailLength) + emailtype;
		newcust.setEmailId(email);
		logger.info("Entered email id");

		newcust.setPassword("hdh#%#_534");
		logger.info("Entered password");

		Thread.sleep(3000);

		newcust.clickSubmit();
		logger.info("Clicked submit button");

		// validate the registration
		logger.info("----Validating registration process----");

		// In real times,real datas are used
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
//registration is success when message matches with expected success message
		if (res == true) {
			Assert.assertTrue(true);
			logger.info("test case passed....");

		} else {
			logger.info("test case failed....");
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}
	}
}
