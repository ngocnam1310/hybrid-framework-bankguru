package com.bankguru.account;

import org.testng.annotations.Test;

import comomns.BaseTest;
import comomns.PageGeneratorManager;
import pageObject.LoginPO;
import pageObject.NewCustomerPO;
import pageObject.RegisterPO;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class ValidationNewCustomer extends BaseTest{
	WebDriver driver;
	RegisterPO register;
	LoginPO login;
	NewCustomerPO newcustomer;
	String email, userid, password;
	String url;
  @Parameters("browser")
  @BeforeClass
  public void beforeClass(String browserName) {
	  driver = getBrowserDriver(browserName);
	  register = PageGeneratorManager.getRegisterPage(driver);
	  email ="autotest" + generateFakeNumber() +"@mail.com";
	  url="http://demo.guru99.com/v4/index.php";
	  register.inputEmailToTextbox(email);
	  userid = register.getUserID();
	  password = register.getPassword();
	  
  }
  
  @Test
  public void TC_01_Verify_Name_Field() {
	  login.openLoginurl(url);
	  login = PageGeneratorManager.getLoginPage(driver);
	  login.loginGuruBank();
	  newcustomer = login.clickToNewCustomer();
	  newcustomer.inputToCustomerNameTextbox("");
	  verifyEquals(newcustomer.getErrorCustomerNameMessage(), "Customer name must not be blank");
	  newcustomer.inputToCustomerNameTextbox(" name123");
	  verifyEquals(newcustomer.getErrorCustomerNameMessage(), "Numbers are not allowed");
	  newcustomer.inputToCustomerNameTextbox("name123");
	  verifyEquals(newcustomer.getErrorCustomerNameMessage(), "First character can not have space");	  
  }
  @Test
  public void TC_02_Address_Field() {
	  newcustomer.inputToAddressTextbox("");
	  verifyEquals(newcustomer.getErrorAddressMessage(), "Address Field must not be blank");
	  newcustomer = login.clickToNewCustomer();
	  newcustomer.inputToCustomerNameTextbox(" name");
	  verifyEquals(newcustomer.getErrorCustomerNameMessage(), "First character can not have space");	  
  }
  @Test
  public void TC_03_City_Field() {
	  newcustomer.inputToCityTextbox("");
	  verifyEquals(newcustomer.getErrorCityMessage(), "City Field must not be blank");
	  newcustomer.inputToCityTextbox(" name");
	  verifyEquals(newcustomer.getErrorCustomerNameMessage(), "First character can not have space");	
	  newcustomer.inputToCityTextbox("name123");
	  verifyEquals(newcustomer.getErrorCustomerNameMessage(), "Numbers are not allowed");
	  newcustomer.inputToCityTextbox("name#12");
	  verifyEquals(newcustomer.getErrorCustomerNameMessage(), "Special characters are not allowed");	
  }
  @Test
  public void TC_04_State_Field() {
	  newcustomer.inputToStateTextbox("");
	  verifyEquals(newcustomer.getErrorStateMessage(), "State must not be blank");
	  newcustomer.inputToStateTextbox(" name");
	  verifyEquals(newcustomer.getErrorStateMessage(), "First character can not have space");	
	  newcustomer.inputToStateTextbox("name123");
	  verifyEquals(newcustomer.getErrorStateMessage(), "Numbers are not allowed");
	  newcustomer.inputToStateTextbox("name#12");
	  verifyEquals(newcustomer.getErrorStateMessage(), "Special characters are not allowed");	
  }
  @Test
  public void TC_05_Pin_Field() {
	  newcustomer.inputToPinTextbox("1234P");
	  verifyEquals(newcustomer.getErrorPinMessage(), "Characters are not allowed");
	  newcustomer.inputToPinTextbox("");
	  verifyEquals(newcustomer.getErrorPinMessage(), "PIN Code must not be blank");
	  newcustomer.inputToPinTextbox("123");
	  verifyEquals(newcustomer.getErrorPinMessage(), "PIN Code must have 6 Digits");	
	  newcustomer.inputToPinTextbox("123@");
	  verifyEquals(newcustomer.getErrorPinMessage(), "Special characters are not allowed");	
	  newcustomer.inputToPinTextbox(" name");
	  verifyEquals(newcustomer.getErrorPinMessage(), "First character can not have space");	
	  newcustomer.inputToPinTextbox("name123");
	  verifyEquals(newcustomer.getErrorPinMessage(), "Numbers are not allowed");
  }
  @Test
  public void TC_06_Telephone_Field() {
	  newcustomer.inputToTelephoneTextbox("");
	  verifyEquals(newcustomer.getErrorTelephoneMessage(), "PIN Code must not be blank");
	  newcustomer.inputToTelephoneTextbox("123");
	  verifyEquals(newcustomer.getErrorTelephoneMessage(), "PIN Code must have 6 Digits");	
	  newcustomer.inputToTelephoneTextbox("123@");
	  verifyEquals(newcustomer.getErrorTelephoneMessage(), "Special characters are not allowed");	
	  newcustomer.inputToTelephoneTextbox(" name");
	  verifyEquals(newcustomer.getErrorTelephoneMessage(), "First character can not have space");	
  }
  @Test
  public void TC_07_Email_Field() {
	  newcustomer.inputToEmailTextbox("");
	  verifyEquals(newcustomer.getErrorEmailMessage(), "Email-ID must not be blank");
	  newcustomer.inputToEmailTextbox("123@");
	  verifyEquals(newcustomer.getErrorEmailMessage(), "Email-ID is not valid");	
	  newcustomer.inputToEmailTextbox(" name");
	  verifyEquals(newcustomer.getErrorEmailMessage(), "Email-ID is not valid");	
  }
  @Test
  public void TC_08_Verify_Field_Labels() {
	  verifyTrue(newcustomer.verifyAllFieldInLables());
  }
  @AfterClass
  public void afterClass() {
  }

}
