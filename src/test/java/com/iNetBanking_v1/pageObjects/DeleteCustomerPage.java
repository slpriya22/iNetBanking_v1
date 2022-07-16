package com.iNetBanking_v1.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DeleteCustomerPage {
	WebDriver ldriver;

	public DeleteCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;// assigning remote driver to local driver..getting remote driver from the base
		// class
		PageFactory.initElements(rdriver, this);// look in the notes
	}

	@FindBy(how = How.XPATH, using = "//body/div[3]/div[1]/ul[1]/li[4]/a[1]")
	@CacheLookup
	WebElement lnkDeleteCustomer;

	@FindBy(xpath = "//input[@name='cusid']")
	@CacheLookup
	WebElement txtCustid;

	@FindBy(xpath = "//tbody/tr[7]/td[2]/input[1]")
	@CacheLookup
	WebElement btnCustIdSubmit;

	@FindBy(xpath = "//tbody/tr[7]/td[2]/input[2]")
	@CacheLookup
	WebElement btnCustIdReset;

	public void clickDeleteCustomerLink() {
		lnkDeleteCustomer.click();
	}

	public void setCustId(String customerid) {
		txtCustid.sendKeys(customerid);
	}

	public void clickCustidSubmitBtn() {
		btnCustIdSubmit.click();
	}

	public void clickCustidResetBtn() {
		btnCustIdReset.click();
	}
}
