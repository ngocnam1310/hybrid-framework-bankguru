package pageObject;

import org.openqa.selenium.WebDriver;

import comomns.BasePage;

public class LoginPO extends BasePage{
	WebDriver driver;
	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}
	public void openLoginurl(String url) {
		openPageUrl(driver, url);		
	}

}
