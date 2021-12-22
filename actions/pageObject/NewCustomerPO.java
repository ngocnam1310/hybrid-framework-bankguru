package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.NewCustomerPageUI;

public class NewCustomerPO extends BasePage{
	WebDriver driver;
	public NewCustomerPO(WebDriver driver) {
		this.driver=driver;
	}
	public boolean verifyAllFieldInLables() {
		waitForElementVisible(driver, NewCustomerPageUI.ALL_FIELD_MY_CUSTOMER);
		return isDisplayElement(driver, NewCustomerPageUI.ALL_FIELD_MY_CUSTOMER);
	}
	public void inputToAddressTextbox(String customerName) {
		waitForElementVisible(driver, NewCustomerPageUI.ADDRESS_FIELD,customerName);
		sendKeyToElement(driver, NewCustomerPageUI.ADDRESS_FIELD,customerName);
		actionTab(driver, NewCustomerPageUI.ADDRESS_FIELD);		
	}
	public String getErrorAddressMessage() {
		waitForElementVisible(driver, NewCustomerPageUI.ERROR_ADDRESS);
		return getElementText(driver, NewCustomerPageUI.ERROR_ADDRESS);
	}
	public String getCustomerID() {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_ID);
		return getElementText(driver, NewCustomerPageUI.CUSTOMER_ID);
	}
	public String verifyCustomerRegisterSuccess() {
		waitForElementVisible(driver, NewCustomerPageUI.CONFIRM_REGISTER_SUCCESS);
		return getElementText(driver, NewCustomerPageUI.CONFIRM_REGISTER_SUCCESS);
	}
	
}
