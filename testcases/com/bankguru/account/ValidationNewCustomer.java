package com.bankguru.account;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import environmentConfig.Environment;
import pageObject.LoginPO;
import pageObject.NewCustomerPO;
import pageObject.RegisterPO;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class ValidationNewCustomer extends BaseTest{
	WebDriver driver;
	RegisterPO register;
	LoginPO login;
	NewCustomerPO newcustomer;
	String email, userid, password;
	Environment environment;
  @Parameters({"browser","url"})
  @BeforeClass
	public void beforeClass(String browserName, String url) {
	  ConfigFactory.setProperty("env", url);
	  environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, environment.appurl());
	  log.info("Pre-condition - Step 01: Open Browser" + browserName + "' and navigate to '" );
	  register = PageGeneratorManager.getRegisterPage(driver);
	  email ="autotest" + generateFakeNumber() +"@mail.com";
	  
	  log.info("Pre-condition - Step 02: Register Account" );
	  register.inputEmailToTextbox(email);
	  register.clickButtonSubmit();
	  userid = register.getUserID();
	  password = register.getPassword();
	
	  
  }
  
  @Test
  public void TC_01_Verify_Name_Field() {
	  log.info("User_01_Verify - Step 01: Open login Page" );
	  register.openPageUrl(driver,GlobalConstants.LOGIN_PAGE_URL);
	  login = PageGeneratorManager.getLoginPage(driver);
	  
	  log.info("User_01_Verify - Step 02: Input userid and password" );
	  login.loginGuruBank(userid,password);
	  
	  log.info("User_01_Verify - Step 03: Select Sub Menu By Name Menu" );
	  newcustomer = login.selectSubMenuBySubName(driver, "New Customer");
	  
	  log.info("User_01_Verify - Step 04: Input Customer Name empty by field name" );
	  newcustomer.inputTextboxByFieldName(driver," ", "name");
	  
	  log.info("User_01_Verify - Step 05: Verify Customer Name empty by field name" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "name"), "Customer name must not be blank");
	  
	  log.info("User_01_Verify - Step 06: Input Customer Name with numberic" );
	  newcustomer.inputTextboxByFieldName(driver, " automantion123","name");
	  
	  log.info("User_01_Verify - Step 07: Verify Customer Name with numberic" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "name"), "Numbers are not allowed");
	  
	  log.info("User_01_Verify - Step 08: Input Customer Name with Special characters" );
	  newcustomer.inputTextboxByFieldName(driver,"@#$automantion","name");
	  
	  log.info("User_01_Verify - Step 09: Verify Customer Name Special characters" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "name"), "Special characters are not allowed");
	  
	  log.info("User_01_Verify - Step 10: Input Customer Name with character Blank space" );
	  newcustomer.inputTextboxByFieldName(driver," automantion","name");
	  
	  log.info("User_01_Verify - Step 11: Verify Customer Name with character Blank space" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "name"), "First character can not have space");	
	  
  }
  @Test
  public void TC_02_Address_Field() {
	  log.info("User_01_Verify - Step 01: Input Address filed is empty" );
	  newcustomer.inputToAddressTextbox(" ");
	  
	  log.info("User_01_Verify - Step 02: Verify Customer Name empty by field name" );
	  verifyEquals(newcustomer.getErrorAddressMessage(), "Address Field must not be blank");

	  log.info("User_01_Verify - Step 03: Input Address filed is empty" );
	  newcustomer.inputToAddressTextbox(" name");
	  
	  log.info("User_01_Verify - Step 04: Verify Customer Name empty by field name" );
	  verifyEquals(newcustomer.getErrorAddressMessage(), "First character can not have space");	  
  }
  @Test
  public void TC_03_City_Field() {
	  log.info("User_01_Verify - Step 01: Input City with empty" );
	  newcustomer.inputTextboxByFieldName(driver," ","city");
	  
	  log.info("User_01_Verify - Step 02: Verify City with empty" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "city"), "City Field must not be blank");
	  
	  log.info("User_01_Verify - Step 03: Input City with First character with character Blank space" );
	  newcustomer.inputTextboxByFieldName(driver," city","city");
	  
	  log.info("User_01_Verify - Step 04: Input City with First character with character Blank space " );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "city"), "First character can not have space");	
	  
	  log.info("User_01_Verify - Step 05: Input City with numberic" );
	  newcustomer.inputTextboxByFieldName(driver,"name123","city");
	  
	  log.info("User_01_Verify - Step 06: Verify City with numberic" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "city"), "Numbers are not allowed");
	  
	  log.info("User_01_Verify - Step 07: Input City with Special character" );
	  newcustomer.inputTextboxByFieldName(driver,"name#12","city");
	  
	  log.info("User_01_Verify - Step 08: Verify City with Special character" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "city"), "Special characters are not allowed");	
  }
  @Test
  public void TC_04_State_Field() {
	  log.info("User_01_Verify - Step 01: Input State with empty" );
	  newcustomer.inputTextboxByFieldName(driver," ","state");
	  
	  log.info("User_01_Verify - Step 02: Verify State with empty" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "state"), "State must not be blank");
	  
	  log.info("User_01_Verify - Step 03: Input State with character Blank space" );
	  newcustomer.inputTextboxByFieldName(driver," name","state");
	  
	  log.info("User_01_Verify - Step 04: Verify State with character Blank space" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "state"), "First character can not have space");	
	  
	  log.info("User_01_Verify - Step 05: Input State with numberic" );
	  newcustomer.inputTextboxByFieldName(driver,"name123","state");
	  
	  log.info("User_01_Verify - Step 06: Verify State with numberic" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "state"), "Numbers are not allowed");
	  
	  log.info("User_01_Verify - Step 07: Input State with Special characters" );
	  newcustomer.inputTextboxByFieldName(driver,"name#12","state");
	  
	  log.info("User_01_Verify - Step 08: Verify State with Special character" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "state"), "Special characters are not allowed");	
  }
  @Test
  public void TC_05_Pin_Field() {
	  log.info("User_01_Verify - Step 01: Input Pin with character value" );
	  newcustomer.inputTextboxByFieldName(driver,"1234P","pinno");
	  
	  log.info("User_01_Verify - Step 02: Verify Customer Name with character value" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "pinno"), "Characters are not allowed");
	  
	  log.info("User_01_Verify - Step 03: Input City with empty" );
	  newcustomer.inputTextboxByFieldName(driver," ","pinno");
	  
	  log.info("User_01_Verify - Step 04: Verify Customer Name with empty" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "pinno"), "PIN Code must not be blank");
	  
	  log.info("User_01_Verify - Step 05: Input City with less than 6 digit" );
	  newcustomer.inputTextboxByFieldName(driver,"123","pinno");
	  
	  log.info("User_01_Verify - Step 06: Verify Customer Name with less than 6 digit" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "pinno"), "PIN Code must have 6 Digits");	
	  
	  log.info("User_01_Verify - Step 07: Input City with Special characters" );
	  newcustomer.inputTextboxByFieldName(driver,"123@","pinno");
	  
	  log.info("User_01_Verify - Step 08: Verify Customer Name with Special character" );;
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "pinno"), "Special characters are not allowed");	
	  
	  log.info("User_01_Verify - Step 09: Input City with character Blank space" );
	  newcustomer.inputTextboxByFieldName(driver," name","pinno");
	  
	  log.info("User_01_Verify - Step 10: Verify Customer Name with character Blank space" );
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "pinno"), "First character can not have space");	

  }
  @Test
  public void TC_06_Telephone_Field() {
	  newcustomer.inputTextboxByFieldName(driver," ","telephoneno");
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "telephoneno"), "Mobile no must not be blank");
	  
	  newcustomer.inputTextboxByFieldName(driver,"123 123","telephoneno");
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "telephoneno"), "Characters are not allowed");	
	  
	  newcustomer.inputTextboxByFieldName(driver,"123@","telephoneno");
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "telephoneno"), "Special characters are not allowed");	
	  
	  newcustomer.inputTextboxByFieldName(driver," name","telephoneno");
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "telephoneno"), "First character can not have space");	
  }
  @Test
  public void TC_07_Email_Field() {
	  newcustomer.inputTextboxByFieldName(driver," ","emailid");
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "emailid"), "Email-ID must not be blank");
	  
	  newcustomer.inputTextboxByFieldName(driver,"123@","emailid");
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "emailid"), "Email-ID is not valid");	
	  
	  newcustomer.inputTextboxByFieldName(driver," name","emailid");
	  verifyEquals(newcustomer.getErrorMessageByFieldName(driver, "emailid"), "Email-ID is not valid");	
  }
  @Test
  public void TC_08_Verify_Field_Labels() {
	  verifyTrue(newcustomer.verifyAllFieldInLables());
  }
	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition: Close browser");
		cleanBrowserAndDriver();
	}

}
