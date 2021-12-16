package pageObject;

import org.openqa.selenium.WebDriver;

import comomns.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPO extends BasePage{
	WebDriver driver;
	public RegisterPO(WebDriver driver) {
		this.driver = driver;
	}
	public void inputEmailToTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXT_BOX_ID);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXT_BOX_ID, email);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}
	public String getUserID() {
		waitForElementVisible(driver,RegisterPageUI.USER_ID);
		return getElementText(driver,RegisterPageUI.USER_ID);
	}
	public String getPassword() {
		waitForElementVisible(driver,RegisterPageUI.PASSWORD);
		return getElementText(driver,RegisterPageUI.PASSWORD);
	}

}
