package com.ctl.it.qa.staf;

import static org.hamcrest.MatcherAssert.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.ctl.it.qa.staf.xml.reader.IntDataContainer;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public abstract class Page extends PageObject {

	public static Environment currentEnvironment;
	public static IntDataContainer data;
	public static IntDataContainer envData;
	public static IntDataContainer commonData;
	public static boolean isInitialized = false;
	private static String parentHandle;
	private static String childHandle;

	public abstract WebElementFacade getUniqueElementInPage();

	public static void initialize() {
		if (!isInitialized) {
			currentEnvironment = Steps.currentEnvironment;
			data = Steps.data;
			envData = Steps.envData;
			commonData = Steps.commonData;
			isInitialized = true;
		}
	}

	public static void switchData() {
		currentEnvironment = Steps.currentEnvironment;
		data = Steps.data;
		envData = Steps.envData;
		commonData = Steps.commonData;
		isInitialized = true;
	}

	public Environment getCurrentEnvironment() {
		return currentEnvironment;
	}

	public void setCurrentEnvironment(Environment env) {
		currentEnvironment = env;
	}

	public void maximize() {
		getDriver().manage().window().maximize();
	}

	public static String getDate(String... format) {
		DateFormat dateFormat;
		if (format.length > 0) {
			dateFormat = new SimpleDateFormat(format[0]);
		} else {
			dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		}
		Date date = new Date();
		return dateFormat.format(date);
	}

	public String getParentHandle() {
		return parentHandle;
	}

	public void setParentHandle() {
		parentHandle = getDriver().getWindowHandle();
	}

	public String getChildHandle() {
		return childHandle;
	}

	public void setChildHandle() {
		for (String winHandle : getDriver().getWindowHandles()) {
			if (!parentHandle.equals(winHandle)) {
				childHandle = winHandle;
				break;
			}
		}
	}

	public void switchToChildWindow() {
		setChildHandle();
		getDriver().switchTo().window(childHandle);
		maximize();
	}

	public void switchToParentWindow() {
		getDriver().switchTo().window(parentHandle);
	}

	public void switchToFrame(String frameName, int... waitTime) {
		WaitForFrameToLoad(frameName, waitTime);
		getDriver().switchTo().frame(frameName);
	}

	public void waitForChildWindowToAppear(int... waitTime) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.getWindowHandles().size() == 2;
			}
		};
		int waitTimeInSeconds;
		if (waitTime.length > 0) {
			waitTimeInSeconds = waitTime[0];
		} else {
			waitTimeInSeconds = 10;
		}
		withTimeoutOf(waitTimeInSeconds, TimeUnit.SECONDS).waitFor(expectation);
	}

	public Wait<WebDriver> Wait(int... waitTime) {
		int waitTimeInSeconds;
		if (waitTime.length > 0) {
			waitTimeInSeconds = waitTime[0];
		} else {
			waitTimeInSeconds = 5;
		}
		return new FluentWait<WebDriver>(getDriver()).withTimeout(waitTimeInSeconds, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class);
	}

	public void WaitForPageToLoad(int... waitTime) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		if (waitTime.length > 0) {
			Wait(waitTime).until(expectation);
		} else {
			Wait(30).until(expectation);
		}
	}

	public void WaitForFrameToLoad(final String frameName, int... waitTime) {
		getDriver().switchTo().defaultContent();
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.switchTo().frame(frameName) != null;
			}
		};
		if (waitTime.length > 0) {
			Wait(waitTime).until(expectation);
		} else {
			Wait(30).until(expectation);
		}
		getDriver().switchTo().defaultContent();
	}

	public void shouldExist(Page page, int... waitTime) {
		WaitForPageToLoad(waitTime);
		WebElementFacade uniqueElement = page.getUniqueElementInPage();
		if (!uniqueElement.isPresent()) {
			waitABit(5000);
		}
		assertThat("User is not in the page : " + page.getClass().getSimpleName(), uniqueElement.isVisible());
	}

	public boolean isExist(Page page, int... waitTime) {
		WaitForPageToLoad(waitTime);
		return page.getUniqueElementInPage().isVisible();
	}
}