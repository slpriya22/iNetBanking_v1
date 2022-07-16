package com.iNetBanking_v1.pageObjects;

//URL- used is "https://demo.guru99.com/V1/index.php"
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;// local driver
	// creating constructor

	public LoginPage(WebDriver rdriver)// remote driver
	{
		ldriver = rdriver;// assigning remote driver to local driver..getting remote driver from the base
							// class
		PageFactory.initElements(rdriver, this);// look in the notes
	}

	@FindBy(name = "uid")
	WebElement txtUserName;// use this name in the action class below

	@FindBy(name = "password")
	@CacheLookup // refer notes
	WebElement txtPassword;

	@FindBy(name = "btnLogin")
	@CacheLookup // for future reference,it makes finding the elements easier by keeping in cache
	WebElement btnLogin;

	@FindBy(name = "btnReset")
	@CacheLookup // for future reference,it makes finding the elements easier by keeping in cache
	WebElement btnReset;

	// for login test using data driventest case
	@FindBy(xpath = "//a[contains(text(),'Log out')]")
	@CacheLookup
	WebElement lnkLogout;

//Writing action methods
	public void setUserName(String uname) {
		txtUserName.sendKeys(uname);// use the name used at @FindBy
		// getting uname from the base class or the input file,sending it to the
		// elements above to initialise
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin() // in training video clickSubmit() is used
	{
		btnLogin.click();
	}

	public void clickReset() {
		btnReset.click();
	}

	public void clickLogout() {
		lnkLogout.click();
	}
}
