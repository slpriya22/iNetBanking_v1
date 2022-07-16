package com.iNetBanking_v1.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddNewCustomerPage {
	WebDriver ldriver;// local driver
	// creating constructor

	public AddNewCustomerPage(WebDriver rdriver)// remote driver
	{
		ldriver = rdriver;// assigning remote driver to local driver..getting remote driver from the base
							// class
		PageFactory.initElements(rdriver, this);// look in the notes
	}

	// can use How keyword in @FindBy too or the other one
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'New Customer')]")
	// @FindBy(xpath = "//a[contains(text(),'New Customer')]")
	@CacheLookup
	WebElement lnkNewCustomer;

	@FindBy(how = How.NAME, using = "name")
	// @FindBy(name = "name")
	@CacheLookup
	WebElement txtCustomerName;// use this name in the action class below

	@FindBy(how = How.XPATH, using = "//input[@value='m']")
	// @FindBy(xpath = "//input[@value='m']")
	@CacheLookup // refer notes
	WebElement rdBtnMale;

	@FindBy(how = How.XPATH, using = "//input[@value='f']")
	// @FindBy(xpath = "//input[@value='f']")
	@CacheLookup // refer notes
	WebElement rdBtnFemale;

	@FindBy(how = How.ID_OR_NAME, using = "dob")
	// @FindBy(id = "//input[@id='dob']")
	@CacheLookup
	WebElement txtdob;

	@FindBy(how = How.NAME, using = "addr")
	// @FindBy(name = "addr")
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

	@FindBy(how = How.NAME, using = "password")
	// @FindBy(name = "password")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(how = How.XPATH, using = "//input[@name='sub']")
	// @FindBy(xpath = "//input[@name='sub']")
	@CacheLookup
	WebElement btnSubmit;

	@FindBy(how = How.XPATH, using = "//input[@name='res']")
	// @FindBy(xpath = "//input[@name='res']")
	@CacheLookup
	WebElement btnReset;

	public void clickNewCustomerLink() {
		lnkNewCustomer.click();
	}

//Writing action methods
	public void setCustomerName(String custName) {
		txtCustomerName.sendKeys(custName);// use the name used at @FindBy
		// getting customer name from the base class or the input file,sending it to the
		// elements above to initialise
	}

	public void clickGender(String gender) {
		if (gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("male")) {
			rdBtnMale.click();// if gender is male
		} else if (gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("female")) {
			rdBtnFemale.click();// if gender is female
		}
	}

	// public void setDob(String mm, String dd, String yyyy) throws
	// InterruptedException {
	public void setDob(String dob) {
		// if it is single text box then once sendKeys are used
		// txtdob.sendKeys(mm);// one by one values are entered
		// txtdob.sendKeys(dd);
		// txtdob.sendKeys(yyyy);//year is entered wrong in this way
		txtdob.sendKeys(dob);
		// if it is calendar pick write selenium webdriver code to pick from calendar as
		// per month,date and year
		// in this example cant locate the calendar button or the month year.So just
		// directly entering the values
	}

	public void setDob1(String mm, String dd, String yyyy)
	// this method is used by pavan training addnew customer test case
	{
		// if it is single text box then once sendKeys are used
		txtdob.sendKeys(mm);// one by one values are entered
		txtdob.sendKeys(dd);
		txtdob.sendKeys(yyyy);// year is entered wrong in this way

		// if it is calendar pick write selenium webdriver code to pick from calendar as
		// per month,date and year
		// in this example cant locate the calendar button or the month year.So just
		// directly entering the values
	}

	public void setAddress(String addr) {
		txtAddress.sendKeys(addr);
	}

	public void setCity(String city) {
		txtCity.sendKeys(city);
	}

	public void setState(String state) {
		txtState.sendKeys(state);
	}

	public void setPin(String pin) // generated random 6 digit numbers for testing purpose
	{
		txtPin.sendKeys(pin);
	}

	public void setMobileNum(String num) // generate some random numbers of 10 digits and send from the test case
	{
		txtMobileNum.sendKeys(num);
	}

	public void setEmailId(String emailid) {
		txtEmailId.sendKeys(emailid);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void clickSubmit() {
		btnSubmit.click();
	}

	public void clickReset() {
		btnReset.click();
	}

}
