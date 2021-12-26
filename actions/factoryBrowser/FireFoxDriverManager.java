package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FireFoxDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver",GlobalConstants.PROJECT_PATH + "\\browserDrivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		return new ChromeDriver(options);
	}

}
