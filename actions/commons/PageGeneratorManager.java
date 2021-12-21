package commons;

import org.openqa.selenium.WebDriver;

import pageObject.EditCustomerPO;
import pageObject.LoginPO;
import pageObject.ManagerPO;
import pageObject.NewCustomerPO;
import pageObject.RegisterPO;

public class PageGeneratorManager {
	public static RegisterPO getRegisterPage(WebDriver driver) {
		return new RegisterPO(driver);	
	}
	public static LoginPO getLoginPage(WebDriver driver) {
		return new LoginPO(driver);
	}
	public static ManagerPO getManagerPage(WebDriver driver) {
		return new ManagerPO(driver);
	}
	public static NewCustomerPO getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPO(driver);
	}
	public static EditCustomerPO getEditCustomerPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new EditCustomerPO(driver);
	}
}
