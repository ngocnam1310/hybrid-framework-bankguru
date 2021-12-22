package com.bankguru.account;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObject.DeleteCustomerPO;
import pageObject.EditCustomerPO;
import pageObject.LoginPO;
import pageObject.NewAccountPO;
import pageObject.NewCustomerPO;
import pageObject.RegisterPO;
import pageUIs.BasePageUI;

public class Payment extends BaseTest{
	String email, userid, password, customerID;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		log.info("Pre-condition - Step 01: Open Browser" + browserName + "' and navigate to '");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		email = "autotest" + generateFakeNumber() + "@mail.com";

		log.info("Pre-condition - Step 02: Register Account");
		registerPage.inputEmailToTextbox(email);
		registerPage.clickButtonSubmit();
		userid = registerPage.getUserID();
		password = registerPage.getPassword();
	}
	@Test
	public void TC01_Create_New_Account() {
		log.info("User_01_Create_Customer - Step 01: Open login Page");
		registerPage.openPageUrl(driver, GlobalConstants.LOGIN_PAGE_URL);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		log.info("Pre-condition - Step 02: Input userid and password");
		loginPage.loginGuruBank(userid, password);

		log.info("Pre-condition - Step 03: Select Sub Menu By Name Menu");
		newcustomerPage = loginPage.selectSubMenuBySubName(driver, "New Customer");

		log.info("Pre-condition - Step 04: Input all field required");
		newcustomerPage.inputTextboxByFieldName(driver, "automation", "name");
		newcustomerPage.removeAttributeInDOM(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, "type", "dob");;
		newcustomerPage.inputTextboxByFieldName(driver, "21/10/2020", "dob");
		newcustomerPage.inputToAddressTextbox("Ha Noi");
		newcustomerPage.inputTextboxByFieldName(driver, "Ha Noi", "city");
		newcustomerPage.inputTextboxByFieldName(driver, "Viet Nam", "state");
		newcustomerPage.inputTextboxByFieldName(driver, "100000", "pinno");
		newcustomerPage.inputTextboxByFieldName(driver, "0123456789", "telephoneno");
		newcustomerPage.inputTextboxByFieldName(driver, email, "emailid");
		newcustomerPage.inputTextboxByFieldName(driver, "123456", "password");
		newcustomerPage.clickButtonByValue(driver, "Submit");
		customerID = newcustomerPage.getCustomerID();
		verifyEquals(newcustomerPage.verifyCustomerRegisterSuccess(), "Customer Registered Successfully!!!"); 
		
	}
	@Test
	public void TC02_Edit_Customer_Account() {
		log.info("User_01_Edit_Customer - Step 01: Open Edit Customer");
		newcustomerPage.selectSubMenuBySubName(driver, "Edit Customer");
		editcustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

		log.info("User_01_Edit_Customer - Step 01: Input customer ID " + customerID);
		editcustomerPage.inputTextboxByFieldName(driver, customerID, "cusid");

		log.info("User_01_Edit_Customer - Step 01: Click to submit");
		editcustomerPage.clickButtonByValue(driver, "Submit");
		
		log.info("User_01_Edit_Customer - Step 01: Update value Address, City, State, Pin");
		editcustomerPage.inputToAddressTextbox("Ha Noi");
		editcustomerPage.inputTextboxByFieldName(driver, "Ha Noi", "city");
		editcustomerPage.inputTextboxByFieldName(driver, "Viet Nam", "state");
		editcustomerPage.inputTextboxByFieldName(driver, "100000", "pinno");
		
		editcustomerPage.clickButtonByValue(driver, "Submit");
		
		
	}
	@Test
	public void TC03_Add_New_Account() {
		log.info("User_01_Add_New_Account - Step 01: Open New Account Page");
		editcustomerPage.selectSubMenuBySubName(driver, "New Account");
		newaccountPage = PageGeneratorManager.getNewAccountPage(driver);
		
		log.info("User_01_Add_New_Account - Step 01: Input data CustomerID , Account Type and Initial deposit");
		newaccountPage.inputTextboxByFieldName(driver, customerID, "cusid");
		newaccountPage.inputTextboxByFieldName(driver, "50000", "inideposit");
		
		log.info("User_01_Add_New_Account - Step 01: Click button submit");
		newaccountPage.clickButtonByValue(driver, "submit");
		
		log.info("User_01_Add_New_Account - Step 01: Verify account generated success");
		verifyEquals(newaccountPage.getMessageSuccessByID(driver, "account"),"Account Generated Successfully!!!");
	}
	@Test
	public void TC04_Edit_Account() {
		
	}
	@Test
	public void TC05_Transfer_Money() {
		
	}
	
	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition: Close browser");
		cleanBrowserAndDriver();
	}
	WebDriver driver;
	RegisterPO registerPage;
	LoginPO loginPage;
	NewCustomerPO newcustomerPage;
	EditCustomerPO editcustomerPage;
	DeleteCustomerPO deletecustomerPage;
	NewAccountPO newaccountPage;
}
