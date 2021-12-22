package commons;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.NewCustomerPO;
import pageUIs.BasePageUI;

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
	public void clickToElement(WebDriver driver,String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();;
	}
	public String getElementText(WebDriver driver,String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	public String getElementText(WebDriver driver,String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}
	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locatorType));
	}
	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}
	
	public void waitForElementVisible(WebDriver driver,String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shorttime);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	public void waitForElementVisible(WebDriver driver,String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shorttime);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	public void waitForElementClickable(WebDriver driver,String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longtime);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	public void waitForElementClickable(WebDriver driver,String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longtime);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void actionTab(WebDriver driver,String locatorType) {
		WebElement element = getWebElement(driver,locatorType);
		element.sendKeys(Keys.TAB);
	}
	public void actionTab(WebDriver driver,String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.sendKeys(Keys.TAB);
	}
	public boolean isDisplayElement(WebDriver driver,String locatorType) {
		WebElement element = getWebElement(driver,locatorType);
		return element.isDisplayed();
	}
	/**/
	public NewCustomerPO selectSubMenuBySubName(WebDriver driver,String... subName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_MENU_SUB_TAB_BY_NAME, subName);
		clickToElement(driver, BasePageUI.DYNAMIC_MENU_SUB_TAB_BY_NAME, subName);
		return PageGeneratorManager.getNewCustomerPage(driver);
	}
	public void inputTextboxByFieldName(WebDriver driver,String value, String... fieldName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, fieldName);
		sendKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME,value, fieldName);
		actionTab(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, fieldName);
	}
	public String getErrorMessageByFieldName(WebDriver driver, String... fieldName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_ERROR_MESSAGE_BY_FIELD_NAME, fieldName);
		return getElementText(driver, BasePageUI.DYNAMIC_ERROR_MESSAGE_BY_FIELD_NAME, fieldName);
	}
	public void clickButtonByValue(WebDriver driver, String... fieldName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_VALUE, fieldName);
		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_VALUE, fieldName);
	}
	public String getMessageSuccessByID(WebDriver driver, String... idFunction) {
		waitForElementVisible(driver, BasePageUI.CONFIRM_MESSAGE_SUCCESS_BY_ID, idFunction);
		return getElementText(driver, BasePageUI.CONFIRM_MESSAGE_SUCCESS_BY_ID, idFunction);		
	}
	
	
	long shorttime = 5;
	long longtime = 20;
	
}
