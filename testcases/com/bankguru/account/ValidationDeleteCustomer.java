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
import pageObject.NewCustomerPO;
import pageObject.RegisterPO;
import pageUIs.BasePageUI;

public class ValidationDeleteCustomer extends BaseTest {
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

		log.info("Pre-condition - Step 01: Open login Page");
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

		log.info("User_01_Edit_Customer - Step 01: Open Edit Customer");
		newcustomerPage.selectSubMenuBySubName(driver, "Delete Customer");
		deletecustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
	}

	@Test
	public void TC_Verify_Delete_Customer_Field() {
		log.info("User_01_Verify_Delete - Step 01: Input Customer ID empty by field name");
		
		deletecustomerPage.inputTextboxByFieldName(driver," ", "cusid");

		log.info("User_01_Verify_Delete - Step 02: Verify Customer ID empty by field name");
		verifyEquals(deletecustomerPage.getErrorMessageByFieldName(driver, "cusid"), "Customer ID is required");

		log.info("User_01_Verify_Delete - Step 03: Input Customer ID with numberic");
		deletecustomerPage.inputTextboxByFieldName(driver, "automantion123", "cusid");

		log.info("User_01_Verify_Delete - Step 04: Verify Customer ID with numberic");
		verifyEquals(deletecustomerPage.getErrorMessageByFieldName(driver, "cusid"), "Characters are not allowed");

		log.info("User_01_Verify_Delete - Step 05: Input Customer ID with Special characters");
		deletecustomerPage.inputTextboxByFieldName(driver, "@#$automantion", "cusid");

		log.info("User_01_Verify_Delete - Step 06: Verify Customer ID Special characters");
		verifyEquals(deletecustomerPage.getErrorMessageByFieldName(driver, "cusid"), "Special characters are not allowed");

		log.info("User_01_Verify_Delete - Step 07: Input Customer ID with character Blank space");
		deletecustomerPage.inputTextboxByFieldName(driver, "auto mantion", "cusid");

		log.info("User_01_Verify_Delete - Step 08: Verify Customer ID with character Blank space");
		verifyEquals(deletecustomerPage.getErrorMessageByFieldName(driver, "cusid"), "Characters are not allowed");
		
		log.info("User_01_Verify_Delete - Step 09: Input Customer ID with first character Blank space");
		deletecustomerPage.inputTextboxByFieldName(driver, " automantion", "cusid");

		log.info("User_01_Verify_Delete - Step 10: Verify Customer ID with first character Blank space");
		verifyEquals(deletecustomerPage.getErrorMessageByFieldName(driver, "cusid"), "First character can not have space");

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
}
