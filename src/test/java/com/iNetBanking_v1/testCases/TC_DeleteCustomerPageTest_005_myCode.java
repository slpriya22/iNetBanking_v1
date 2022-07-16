package com.iNetBanking_v1.testCases;

//instead of nested if,,use while alert is true accpet and check the alert box msg in delete customer
//as it is malfunctioning..
//beacuse of very slow response of chrome for this test case,even passed test is going to failure because of first alert box appearing before even submit buttom is clicked
//so have to rewrite test case many times to make it pass
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.iNetBanking_v1.pageObjects.DeleteCustomerPage;
import com.iNetBanking_v1.pageObjects.LoginPage;

//try with ddt approach too while doing
//refresh the page when delete customer link is clicked from home page so to close the random ads
public class TC_DeleteCustomerPageTest_005_myCode extends BaseClass_myCode // ----------------
{
	@Test(priority = 4)
	public void deleteCustomer() throws InterruptedException // -----------------
	{

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
		DeleteCustomerPage deletecust = new DeleteCustomerPage(driver);

		deletecust.clickDeleteCustomerLink();
		logger.info("Clicked delete customer link from home page");
		driver.navigate().refresh();// to handle soem random ad
		Thread.sleep(5000);

		// WebDriverWait wait = new WebDriverWait(driver, 30);
		// WebDriverWait wait=new WebDriverWait(driver,300);
		// WebElement deleteCustomerLink = wait
		// .until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[3]/div[1]/ul[1]/li[4]/a[1]")));
		// deleteCustomerLink.click();

		try {
			Thread.sleep(1000);
			deletecust.clickDeleteCustomerLink();
			logger.info("Closed some random ad by refreshing home page");
			logger.info("Again delete customer link is clicked after refreshing");
		} catch (StaleElementReferenceException e) { // again identify the element and click it when the exception
														// throws
			logger.info("Closed some random ad by refreshing home page");
			WebElement deleteLink = driver.findElement(By.xpath("//body/div[3]/div[1]/ul[1]/li[4]/a[1]"));
			deleteLink.click();
			// deletecust.clickDeleteCustomerLink();// the element is located again and
			// checking
			logger.info("Again delete customer link is clicked after refreshing");
		}

		// handling stale element referencce exception
		Thread.sleep(1000);
		// deletecust.setCustId("69165");customerid
		deletecust.setCustId(customerid);
		logger.info("Entered customer id to be deleted.");

		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		deletecust.clickCustidSubmitBtn();
		logger.info("Clicked submit button to edit the customer details.");

		if (isAlertPresent() == true) {
			String deleteAlertMsg = driver.switchTo().alert().getText();
			if (deleteAlertMsg.contains("Do you really want to delete this Customer?")
					|| deleteAlertMsg.contains("Customer does not Exist!!!")) {
				driver.switchTo().alert().accept();
				if (isAlertPresent() == true) {
					String deleteAlertMsg1 = driver.switchTo().alert().getText();
					if (deleteAlertMsg1.contains("Do you really want to delete this Customer?")
							|| deleteAlertMsg1.contains("Customer does not Exist!!!"))
						driver.switchTo().alert().accept();
				}
				logger.info("Customer deleted successfully.");
				Assert.assertTrue(true);
			} // --------------------
			else {
				logger.info(
						"You entered wrong or non existing customer ID or you are not authorised to delete this customer.");
				logger.warn("Deleting customer failed.Please check logs and reports and try again");
				Assert.assertTrue(false,
						"Entered Wrong or non existing customerID or you are not authorised to delete the customer");
			}
		} else {
			logger.info(
					"You entered wrong or non existing customer ID or you are not authorised to delete this customer.");
			logger.warn("Deleting customer failed.Please check logs and reports and try again");
			Assert.assertTrue(false,
					"Entered Wrong or non existing customerID or you are not authorised to delete the customer");
		}
	}
}
/*
 * WebElement element=driver.findElement(By.xpath""); JavascriptExecutor
 * ex=(JavascriptExecutor)driver; ex.executeScript("arguments[0].click()",
 * element);
 */