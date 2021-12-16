package comomns;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public BasePage() {
		// TODO Auto-generated constructor stub
	}
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	private By getByLocator(String loactorType) {
		By by = null;
		if (loactorType.startsWith("id=") || loactorType.startsWith("ID=") || loactorType.startsWith("Id=")) {
			by = By.id(loactorType.substring(3));
		} else if (loactorType.startsWith("class=") || loactorType.startsWith("CLASS=")
				|| loactorType.startsWith("Class=")) {
			by = By.className(loactorType.substring(6));
		} else if (loactorType.startsWith("name=") || loactorType.startsWith("NAME=")
				|| loactorType.startsWith("Name=")) {
			by = By.name(loactorType.substring(5));
		} else if (loactorType.startsWith("css=") || loactorType.startsWith("CSS=") || loactorType.startsWith("Css=")) {
			by = By.cssSelector(loactorType.substring(4));
		} else if (loactorType.startsWith("xpath=") || loactorType.startsWith("Xpath=")
				|| loactorType.startsWith("XPATH=") || loactorType.startsWith("XPath=")) {
			by = By.xpath(loactorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported!");
		}
		return by;

	}

	private String getDynamicXpath(String loactorType, String... dynamicValues) {
		if (loactorType.startsWith("xpath=") || loactorType.startsWith("Xpath=") || loactorType.startsWith("XPATH=")
				|| loactorType.startsWith("XPath=")) {
			loactorType = String.format(loactorType, (Object[]) dynamicValues);
		}
		return loactorType;

	}
	private WebElement getWebElement(WebDriver driver, String loactorType) {
		return driver.findElement(getByLocator(loactorType));
	}

	private WebElement getWebElement(WebDriver driver, String loactorType, String... dynamicValues) {
		return driver.findElement(getByLocator(getDynamicXpath(loactorType, dynamicValues)));
	}

	private List<WebElement> getListWebElements(WebDriver driver, String loactorType) {
		return driver.findElements(getByLocator(loactorType));
	}
	public void sendKeyToElement(WebDriver driver,String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	public void clickToElement(WebDriver driver,String locatorType) {
		getWebElement(driver, locatorType).click();;
	}
	public String getElementText(WebDriver driver,String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locatorType));
	}
	
	public void waitForElementVisible(WebDriver driver,String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shorttime);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	public void waitForElementClickable(WebDriver driver,String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longtime);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	
	
	
	
	long shorttime = 5;
	long longtime = 20;
	
}
