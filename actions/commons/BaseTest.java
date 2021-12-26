package commons;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	protected WebDriver getBrowserDriver(String browserName) {
		System.out.println("Run on " + browserName);
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", GlobalConstants.PROJECT_PATH + "\\browserDrivers\\geckodriver.exe");
			// WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			// WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", GlobalConstants.PROJECT_PATH + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			//WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver", GlobalConstants.PROJECT_PATH + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name invalid");
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.REGISTER_PAGE_URL);
		return driver;

	}
	protected WebDriver getBrowserDriver(String browserName, String url) {
		System.out.println("Run on " + browserName);
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", GlobalConstants.PROJECT_PATH + "\\browserDrivers\\geckodriver.exe");
			// WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			// WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", GlobalConstants.PROJECT_PATH + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			//WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver", GlobalConstants.PROJECT_PATH + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name invalid");
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		return driver;

	}
	protected WebDriver getBrowserDriverBrowserstack(String browserName, String appUrl, String osName, String osVerison) {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("os", osName);
		capability.setCapability("os_version", osVerison);
		capability.setCapability("browser", browserName);
		capability.setCapability("browser_version", "latest");
		capability.setCapability("browserstack.debug", "true");
		capability.setCapability("name", "Run on" + osName + " and " + browserName);
		try {
			driver= new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), capability);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.REGISTER_PAGE_URL);
		return driver;

	}
//	private String getEnvironmentValue(String environmentName) {
//		String envUrl = null;
//		ENVIRONMENT environment = ENVIRONMENT.valueOf(environmentName.toUpperCase());
//		if(environment== ENVIRONMENT.DEV) {
//			envUrl="";
//		}else if (environment== ENVIRONMENT.TESTING) {
//			envUrl="";
//		}
//		return envUrl;
//	}

	public WebDriver getWebDriver() {
		return this.driver;
	}

	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;

		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	protected void cleanBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + osName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
