package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPO extends BasePage{
	WebDriver driver;
	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}
	public void loginGuruBank(String userid, String password) {
		waitForElementVisible(driver, LoginPageUI.USER_ID_TEXT_BOX);
		sendKeyToElement(driver, LoginPageUI.USER_ID_TEXT_BOX, userid);
		
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXT_BOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXT_BOX, password);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

}
