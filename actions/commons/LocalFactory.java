package commons;

import org.openqa.selenium.WebDriver;

import factoryBrowser.BROWSER;
import factoryBrowser.BrowserNotSupportedException;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FireFoxDriverManager;

public class LocalFactory {
	private String browserName;
	WebDriver driver;
	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}

	public WebDriver createDriver() {
		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		switch (browser) {
		case CHROME:
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		case FIREFOX:
			driver = new FireFoxDriverManager().getBrowserDriver();
			break;
		case EDGE_CHROMIUM:
			driver = new EdgeDriverManager().getBrowserDriver();
			break;

		default:
			throw new BrowserNotSupportedException(browserName);
		}
		return driver;
		
	}
	
}
