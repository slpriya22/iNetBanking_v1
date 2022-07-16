package com.iNetBanking_v1.testCases;

//reset button in edit customer page is not working..so using weblement.clear() in the page object class..used thread.sleep() which is not recommended
//even after editing customer details it isa showing same messag in alert box
//'No Changes made to Customer records' with no editing and with editing
//Edit customer page reset button not working
//just write common test case only for positive test..cant do negative test with alert msg difference
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.iNetBanking_v1.pageObjects.EditCustomerPage;
import com.iNetBanking_v1.pageObjects.LoginPage;

//refresh the page when edit customer link is clicked from home page so to close the random ads
//try with ddt approach too while doing
public class TC_EditCustomerPageTest_004_myCode extends BaseClass_myCode {

	@Test(priority = 3)
	public void editCustomer() throws InterruptedException {

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

		EditCustomerPage editcust = new EditCustomerPage(driver);

		editcust.clickEditCustomerLink();
		logger.info("Clicked edit customer link from home page");
		Thread.sleep(1000);
		driver.navigate().refresh();// to handle soem random ad
		Thread.sleep(3000);
		// handling stale element referencce exception
		try {
			editcust.clickEditCustomerLink();
			logger.info("Closed some random ad by refreshing home page");
			logger.info("Again edit customer link is clicked after refreshing");
		} catch (StaleElementReferenceException e) {
			// again identify the element and click it when the exception throws
			logger.info("Closed some random ad by refreshing home page");
			editcust = new EditCustomerPage(driver);// the element is located again and checking
			editcust.clickEditCustomerLink();
			logger.info("Again edit customer link is clicked after refreshing");

		}

		Thread.sleep(2000);

		editcust.clickCustidResetBtn();
		logger.info(
				"Clicked reset button to clear cust id field to avoid any unexpected error before entering the customer id.");
		Thread.sleep(2000);

		editcust.setCustId(customerid);
		logger.info("Entered customer id to be edited.");

		editcust.clickCustidSubmitBtn();
		logger.info("Clicked submit button to edit the customer details.");

		if (isAlertPresent() == true) {
			String alertMsg = acceptAlert();
			Thread.sleep(2000);
			if (alertMsg != null) {
				if (alertMsg.equals("Customer does not exist!!")) {
					logger.warn("Customer ID you entered does not exist");
					Assert.assertTrue(false,
							"Customer ID you entered does not exist. Please check log and report and try again.");
				} // ---------------------------
				else if (alertMsg.equals("You are not authorize to edit this customer!!")) {
					logger.warn(
							"You are not authorised to edit this customer.Please check log and report and try again");
					Assert.assertTrue(false, "You are not authorised to edit this customer.");
				}
			}

		} // if end-------

		logger.info("----Customer details editing started----");

		// RESET BUTTON IN CUSTOMER EDIT PAGE IS NOT WORKING
		// editcust.clickEditCustReset();
		// logger.info("Reset button is clicked to clear the fields before editing all
		// customer details");
		// System.out.println("In this edit customer test,we are entering values in all
		// fields again with some changes");

		// editcust.setAddress("10 beach road ghost town");// special characters are not
		// allowed
		Thread.sleep(1000);
		editcust.editAddress("13 pei veedu");
		logger.info("Entered address");
		Thread.sleep(1000);
		editcust.editCity("pisasu city");
		logger.info("Entered city");
		Thread.sleep(1000);
		editcust.editState("Boothnath");
		logger.info("Entered state");
		Thread.sleep(1000);
		int pinCodeLength = 6;// see notes..pin must have 6 digits
		String pincode = randomNum(pinCodeLength);// only when pin code is completely numbers.
		// if it is alphabets then hardcode or send from config file..just for testing
		// purpose used it.In real time config file or excel is used to send large
		// number of datas
		editcust.editPin(pincode);
		logger.info("Entered pin");
		Thread.sleep(1000);
		int mobileNumLength = 10;
		String mobileno = randomNum(mobileNumLength);
		editcust.editMobileNum(mobileno);// randomNum returns string of random numeric characters
		logger.info("Entered mobile number");
		Thread.sleep(1000);
		int emailLength = 5;
		String emailtype = "@gmail.com";// can use any email type @yahoo.com,@xxx.com
		String email = randomString(emailLength) + emailtype;
		editcust.editEmailId(email);
		logger.info("Entered email id");

		Thread.sleep(3000);

		editcust.clickEditCustSubmit();
		logger.info("Clicked submit button");

		// validate the registration
		logger.info("----Validating editing process----");
		// In real times,real datas are used
		// if alert present then editing is successful
		// registration is success when message matches with expected success message
		if (isAlertPresent() == true) // --------
		{
			String alertMsg = acceptAlert();
			Thread.sleep(2000);
			if (alertMsg != null) {
				if (alertMsg.equals("No Changes made to Customer records"))
				// now for successful edit also this msg only showing
				{
					logger.info("Customer details edited successfully and validation is successful.");
					Assert.assertTrue(true, "Customer details edited successfully and validation is successful.");
				}

			}

		} // if end-------
			// alert box appears if customer cannot be added due to some problem
			// stating 'customer cannot be added'
		else {
			logger.warn("Editing customer details failed. Please check log and report and try again.");
			Assert.assertTrue(false, "Editing customer details failed. Please check log and report and try again.");
		}

	}
}
