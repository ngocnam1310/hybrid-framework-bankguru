package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.EditCustomerPageUI;
public class EditCustomerPO extends BasePage{
	WebDriver driver;
	public EditCustomerPO(WebDriver driver) {
		this.driver=driver;
	}
	public void inputToAddressTextbox(String customerName) {
		waitForElementVisible(driver, EditCustomerPageUI.ADDRESS_FIELD,customerName);
		sendKeyToElement(driver, EditCustomerPageUI.ADDRESS_FIELD,customerName);
		actionTab(driver, EditCustomerPageUI.ADDRESS_FIELD);		
		
	}
	public Object getErrorAddressMessage() {
		waitForElementVisible(driver, EditCustomerPageUI.ERROR_ADDRESS);
		return getElementText(driver, EditCustomerPageUI.ERROR_ADDRESS);
	}
	
}
