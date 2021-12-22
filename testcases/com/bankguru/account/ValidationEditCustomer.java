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

public class ValidationEditCustomer extends BaseTest {
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
		newcustomerPage.selectSubMenuBySubName(driver, "Edit Customer");
		editcustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

		log.info("User_01_Edit_Customer - Step 01: Input customer ID " + customerID);
		editcustomerPage.inputTextboxByFieldName(driver, customerID, "cusid");

		log.info("User_01_Edit_Customer - Step 01: Click to submit");
		editcustomerPage.clickButtonByValue(driver, "Submit");
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

	@Test
	public void TC_02_Verify_Edit_Customer_Adress_Field() {
		log.info("User_01_Verify - Step 01: Input Address filed is empty");
		editcustomerPage.inputToAddressTextbox(" ");

		log.info("User_01_Verify - Step 02: Verify Customer Name empty by field name");
		verifyEquals(editcustomerPage.getErrorAddressMessage(), "Address Field must not be blank");
	}

	@Test
	public void TC_03_Verify_Edit_Customer_City_Field() {
		log.info("User_01_Verify - Step 01: Input City with empty");
		editcustomerPage.inputTextboxByFieldName(driver, " ", "city");

		log.info("User_01_Verify - Step 02: Verify City with empty");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "city"), "City Field must not be blank");

		log.info("User_01_Verify - Step 05: Input City with numberic");
		editcustomerPage.inputTextboxByFieldName(driver, "name123", "city");

		log.info("User_01_Verify - Step 06: Verify City with numberic");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "city"), "Numbers are not allowed");

		log.info("User_01_Verify - Step 07: Input City with Special character");
		editcustomerPage.inputTextboxByFieldName(driver, "name#12", "city");

		log.info("User_01_Verify - Step 08: Verify City with Special character");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "city"), "Special characters are not allowed");
	}

	@Test
	public void TC_04_Verify_Edit_Customer_State_Field() {
		log.info("User_01_Verify - Step 01: Input State with empty");
		editcustomerPage.inputTextboxByFieldName(driver, " ", "state");

		log.info("User_01_Verify - Step 02: Verify State with empty");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "state"), "State must not be blank");

		log.info("User_01_Verify - Step 05: Input State with numberic");
		editcustomerPage.inputTextboxByFieldName(driver, "name123", "state");

		log.info("User_01_Verify - Step 06: Verify State with numberic");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "state"), "Numbers are not allowed");

		log.info("User_01_Verify - Step 07: Input State with Special characters");
		editcustomerPage.inputTextboxByFieldName(driver, "name#12", "state");

		log.info("User_01_Verify - Step 08: Verify State with Special character");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "state"),
				"Special characters are not allowed");
	}

	@Test
	public void TC_05_Verify_Edit_Customer_Pin_Field() {
		log.info("User_01_Verify - Step 01: Input Pin with character value");
		editcustomerPage.inputTextboxByFieldName(driver, "1234P", "pinno");

		log.info("User_01_Verify - Step 02: Verify Customer Name with character value");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "pinno"), "Characters are not allowed");

		log.info("User_01_Verify - Step 03: Input City with empty");
		editcustomerPage.inputTextboxByFieldName(driver, " ", "pinno");

		log.info("User_01_Verify - Step 04: Verify Customer Name with empty");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "pinno"), "PIN Code must not be blank");

		log.info("User_01_Verify - Step 05: Input City with less than 6 digit");
		editcustomerPage.inputTextboxByFieldName(driver, "123", "pinno");

		log.info("User_01_Verify - Step 06: Verify Customer Name with less than 6 digit");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "pinno"), "PIN Code must have 6 Digits");

		log.info("User_01_Verify - Step 07: Input City with Special characters");
		editcustomerPage.inputTextboxByFieldName(driver, "123@", "pinno");

		log.info("User_01_Verify - Step 08: Verify Customer Name with Special character");
		;
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "pinno"),
				"Special characters are not allowed");
	}

	@Test
	public void TC_06_Verify_Edit_Customer_Telephone_Field() {
		editcustomerPage.inputTextboxByFieldName(driver, " ", "telephoneno");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "telephoneno"), "Mobile no must not be blank");

		editcustomerPage.inputTextboxByFieldName(driver, "123 123", "telephoneno");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "telephoneno"), "Characters are not allowed");

		editcustomerPage.inputTextboxByFieldName(driver, "123@", "telephoneno");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "telephoneno"),
				"Special characters are not allowed");

		editcustomerPage.inputTextboxByFieldName(driver, " name", "telephoneno");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "telephoneno"),
				"First character can not have space");
	}

	@Test
	public void TC_07_Verify_Edit_Customer_Email_Field() {
		editcustomerPage.inputTextboxByFieldName(driver, " ", "emailid");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "emailid"), "Email-ID must not be blank");

		editcustomerPage.inputTextboxByFieldName(driver, "123@", "emailid");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "emailid"), "Email-ID is not valid");

		editcustomerPage.inputTextboxByFieldName(driver, " name", "emailid");
		verifyEquals(editcustomerPage.getErrorMessageByFieldName(driver, "emailid"), "Email-ID is not valid");
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
