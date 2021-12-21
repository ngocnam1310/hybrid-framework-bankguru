package com.bankguru.account;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObject.EditCustomerPO;
import pageObject.LoginPO;
import pageObject.NewCustomerPO;
import pageObject.RegisterPO;
import pageUIs.BasePageUI;

public class DeleteCustomer extends BaseTest {
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
	}

	@Test
	public void TC_01_Verify_Edit_Customer_Name_Field() {
		log.info("User_01_Verify - Step 04: Input Customer Name empty by field name");
		editcustomerPage.inputTextboxByFieldName(driver," ", "name");

		log.info("User_01_Verify - Step 05: Verify Customer Name empty by field name");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "name"), "Customer name must not be blank");

		log.info("User_01_Verify - Step 06: Input Customer Name with numberic");
		editcustomerPage.inputTextboxByFieldName(driver, " automantion123", "name");

		log.info("User_01_Verify - Step 07: Verify Customer Name with numberic");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "name"), "Numbers are not allowed");

		log.info("User_01_Verify - Step 08: Input Customer Name with Special characters");
		editcustomerPage.inputTextboxByFieldName(driver, "@#$automantion", "name");

		log.info("User_01_Verify - Step 09: Verify Customer Name Special characters");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "name"), "Special characters are not allowed");

		log.info("User_01_Verify - Step 10: Input Customer Name with character Blank space");
		editcustomerPage.inputTextboxByFieldName(driver, " automantion", "name");

		log.info("User_01_Verify - Step 11: Verify Customer Name with character Blank space");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "name"), "First character can not have space");

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
}
