package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.RegisterPageUI;

public class RegisterPO extends BasePage{
	WebDriver driver;
	public RegisterPO(WebDriver driver) {
		this.driver = driver;
	}
	public void inputEmailToTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXT_BOX_ID);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXT_BOX_ID, email);
	}
	public String getUserID() {
		waitForElementVisible(driver,RegisterPageUI.USER_ID);
		return getElementText(driver,RegisterPageUI.USER_ID);
	}
	public String getPassword() {
		waitForElementVisible(driver,RegisterPageUI.PASSWORD);
		return getElementText(driver,RegisterPageUI.PASSWORD);
	}
	public RegisterPO clickButtonSubmit() {
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
		return PageGeneratorManager.getRegisterPage(driver);	
	}

}
