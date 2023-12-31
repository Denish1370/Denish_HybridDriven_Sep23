package base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.ConstantsPath;
import utility.PropOperations;

public class ControlActions {

	protected static WebDriver driver;
	private static PropOperations propOperations;
	private static WebDriverWait wait;

	public static void launchBrowser() {
		propOperations = new PropOperations(ConstantsPath.DEV_ENV_FILEPATH);
		System.setProperty(ConstantsPath.CHROME_DRIVER_KEY, ConstantsPath.CHROME_DRIVER_VALUE);
		driver = new ChromeDriver();
		driver.get(propOperations.getValue("url"));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, ConstantsPath.WAIT);
	}

	protected WebElement getElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement e = null;
		switch (locatorType.toUpperCase()) {
		case "XPATH":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			else
				e = driver.findElement(By.xpath(locatorValue));
			break;

		case "CSS":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			else
				e = driver.findElement(By.cssSelector(locatorValue));
			break;

		case "ID":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			else
				e = driver.findElement(By.id(locatorValue));
			break;

		case "NAME":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			else
				e = driver.findElement(By.name(locatorValue));
			break;

		case "LINKTEXT":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			else
				e = driver.findElement(By.linkText(locatorValue));
			break;

		case "PARTIALLINKTEXT":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			else
				e = driver.findElement(By.partialLinkText(locatorValue));
			break;

		case "CLASSNAME":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			else
				e = driver.findElement(By.className(locatorValue));
			break;

		case "TAGNAME":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			else
				e = driver.findElement(By.tagName(locatorValue));
			break;

		default:
			System.out.println("Locator is invalid");
		}

		return e;
	}
	
	protected void waitForElementToBeVisible(WebElement e) {
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	protected void waitForElementToBeClickable(WebElement e) {
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}
	
	protected void waitForElementToBeInvisible(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, ConstantsPath.FAST_WAIT);
		wait.until(ExpectedConditions.invisibilityOf(e));
	}
	
	protected boolean isElementDisplayed(WebElement e) {
		try {
			return e.isDisplayed();
		}catch(NoSuchElementException ne) {
			return false;
		}
	}
	
	protected boolean isElementDisplayedWithWait(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
			return true;
		}catch(NoSuchElementException ne) {
			return false;
		}
	}
	
	protected boolean isElementDisplayedWithWait(WebElement e, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOf(e));
			return true;
		}catch(Exception ne) {
			return false;
		}
	}
	
	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public static void closeBrowser() {
		driver.close();
	}
}
