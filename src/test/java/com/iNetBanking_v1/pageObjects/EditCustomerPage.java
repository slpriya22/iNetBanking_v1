package com.iNetBanking_v1.pageObjects;

//use explicit wait instead of Thread.sleep..Thread.sleep(0mis not recommended.
//just using here temporary.In real test case use explicit wait statement
//have to use element.clear() as reset button of the page is not working
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class EditCustomerPage {
	WebDriver ldriver;// local driver
	// creating constructor

	public EditCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;// assigning remote driver to local driver..getting remote driver from the base
		// class
		PageFactory.initElements(rdriver, this);// look in the notes
	}

	// can use How keyword in @FindBy too or the other one
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Edit Customer')]")
	@CacheLookup
	WebElement lnkEditCustomer;

	@FindBy(xpath = "//input[@name='cusid']")
	@CacheLookup
	WebElement txtCustid;

	@FindBy(xpath = "//tbody/tr[11]/td[2]/input[1]")
	@CacheLookup
	WebElement btnCustIdSubmit;

	@FindBy(xpath = "//tbody/tr[11]/td[2]/input[2]")
	@CacheLookup
	WebElement btnCustIdReset;

	@FindBy(name = "addr")
	@CacheLookup
	WebElement txtAddress;

	@FindBy(how = How.NAME, using = "city")
	// @FindBy(name = "city")
	@CacheLookup
	WebElement txtCity;

	@FindBy(how = How.NAME, using = "state")
	// @FindBy(name = "state")
	@CacheLookup
	WebElement txtState;

	@FindBy(how = How.NAME, using = "pinno")
	// @FindBy(name = "pinno")
	@CacheLookup
	WebElement txtPin;

	@FindBy(how = How.NAME, using = "telephoneno")
	// @FindBy(name = "telephoneno")
	@CacheLookup
	WebElement txtMobileNum;

	@FindBy(how = How.NAME, using = "emailid")
	// @FindBy(name = "emailid")
	@CacheLookup
	WebElement txtEmailId;

	@FindBy(how = How.XPATH, using = " //tbody/tr[13]/td[2]/input[1]")
	// @FindBy(xpath = "//input[@name='sub']")
	@CacheLookup
	WebElement btnEditCustSubmit;

	@FindBy(how = How.XPATH, using = " //tbody/tr[13]/td[2]/input[2]")
	// @FindBy(xpath = "//input[@name='res']")
	@CacheLookup
	WebElement btnEditCustReset;

	public void clickEditCustomerLink() {
		lnkEditCustomer.click();
	}

	public void setCustId(String customerid) {
		txtCustid.sendKeys(customerid);
	}

	public void clickCustidResetBtn() {
		btnCustIdReset.click();
	}

	public void clickCustidSubmitBtn() {
		btnCustIdSubmit.click();
	}

	public void editAddress(String addr) throws InterruptedException {
		txtAddress.clear();// edit page reset is not working..without resetting values are getting amended
							// not replaced
		Thread.sleep(1000);// it is not recommendable to use thread.sleep..use explicit wait in real time
		txtAddress.sendKeys(addr);
	}

	public void editCity(String city) throws InterruptedException {
		txtCity.clear();// edit page reset is not working..without resetting values are getting amended
						// not replaced
		Thread.sleep(1000);
		txtCity.sendKeys(city);
	}

	public void editState(String state) throws InterruptedException {
		txtState.clear();// edit page reset is not working..without resetting values are getting amended
							// not replaced
		Thread.sleep(1000);
		txtState.sendKeys(state);
	}

	public void editPin(String pin) throws InterruptedException // generated random 6 digit numbers for testing purpose
	{
		txtPin.clear();// edit page reset is not working..without resetting values are getting amended
						// not replaced
		Thread.sleep(1000);
		txtPin.sendKeys(pin);
	}

	public void editMobileNum(String num) throws InterruptedException // generate some random numbers of 10 digits and
																		// send from the test case
	{
		txtMobileNum.clear();// edit page reset is not working..without resetting values are getting amended
								// not replaced
		Thread.sleep(1000);
		txtMobileNum.sendKeys(num);
	}

	public void editEmailId(String emailid) throws InterruptedException {
		txtEmailId.clear();// edit page reset is not working..without resetting values are getting amended
							// not replaced
		Thread.sleep(1000);
		txtEmailId.sendKeys(emailid);
	}

	public void clickEditCustSubmit() {
		btnEditCustSubmit.click();
	}

	public void clickEditCustReset() {
		btnEditCustReset.click();
	}

}
