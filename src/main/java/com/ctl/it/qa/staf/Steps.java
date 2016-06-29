package com.ctl.it.qa.staf;

import static org.hamcrest.MatcherAssert.assertThat;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import net.thucydides.core.steps.ScenarioSteps;

import com.ctl.it.qa.staf.xml.reader.IntContainerField;
import com.ctl.it.qa.staf.xml.reader.IntDataContainer;
import com.ctl.it.qa.staf.xml.reader.XMLDataParser;

@SuppressWarnings("serial")
public class Steps extends ScenarioSteps {

	protected static Environment currentEnvironment;
	protected static IntDataContainer data;
	protected static IntDataContainer envData;
	protected static IntDataContainer commonData;
	private static STAFController controller;
	public static boolean isInitialized = false;
	private static String parentHandle;
	private static String childHandle;

	private AutoPopulate autoPopulate = new AutoPopulate();
	private AutoVerify autoVerify = new AutoVerify();
	
	public static void initialize(String xmlName) {
		if (!isInitialized) {
			initEnv();
			initData(xmlName);
			Page.initialize();
		}
	}
	
	private static void initEnv() {
		controller = STAFController.getInstance();

		if (!controller.isInitialized()) {
			throw new IllegalStateException("STAF Controller is not initialized. Please contact Framework Administrator.");
		}
		currentEnvironment = controller.getEnvironment();
	}
	
	private static void initData(String xmlName) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(xmlName);
		data = new XMLDataParser().parseXML(url.toString());
		envData = data.getContainer("Environments").getContainer(currentEnvironment.getKey());
		commonData = data.getContainer("CommonData");
		isInitialized = true;
	}
	
	public static void switchDataTo(String xmlName) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(xmlName);
		data = new XMLDataParser().parseXML(url.toString());
		envData = data.getContainer("Environments").getContainer(currentEnvironment.getKey());
		commonData = data.getContainer("CommonData");
		Page.switchData();
	}

	public void maximize() {
		getDriver().manage().window().maximize();
	}

	public void fillMandatoryFields(Page page) {
		IntDataContainer dataContainer = commonData.getContainer(page.getClass().getSimpleName());
		dataContainer.setActualValues();
		autoPopulate.populateValues(page,
				dataContainer.getMandatoryFields());
	}

	public void fillAllFields(Page page) {
		IntDataContainer dataContainer = commonData.getContainer(page.getClass().getSimpleName());
		dataContainer.setActualValues();
		autoPopulate.populateValues(page,
				dataContainer.getFields());
	}

	public void fillMandatoryFields(Page page, IntDataContainer dataContainer) {
		dataContainer.setActualValues();
		autoPopulate.populateValues(page,
				dataContainer.getMandatoryFields());
	}

	public void fillAllFields(Page page, IntDataContainer dataContainer) {
		dataContainer.setActualValues();
		autoPopulate.populateValues(page,
				dataContainer.getFields());
	}

	public void fillMandatoryFieldsFromAllContainers(Page page) {
		IntDataContainer dataContainer = commonData.getContainer(page.getClass().getSimpleName());
		dataContainer.setActualValuesForAllContainers();
		autoPopulate.populateValues(page,
				dataContainer.getMandatoryFieldsFromAllContainers());
	}

	public void fillAllFieldsFromAllContainers(Page page) {
		IntDataContainer dataContainer = commonData.getContainer(page.getClass().getSimpleName());
		dataContainer.setActualValuesForAllContainers();
		autoPopulate.populateValues(page,
				dataContainer.getFieldsFromAllContainers());
	}

	public void fillMandatoryFieldsFromAllContainers(Page page, IntDataContainer dataContainer) {
		dataContainer.setActualValuesForAllContainers();
		autoPopulate.populateValues(page,
				dataContainer.getMandatoryFieldsFromAllContainers());
	}

	public void fillAllFieldsFromAllContainers(Page page, IntDataContainer dataContainer) {
		dataContainer.setActualValuesForAllContainers();
		autoPopulate.populateValues(page,
				dataContainer.getFieldsFromAllContainers());
	}
	
	public void fillFields(Page page, List<IntContainerField> fields) {
		autoPopulate.populateValues(page, fields);
	}

	public void verifyMandatoryFields(Page page) {
		IntDataContainer dataContainer = commonData.getContainer(page.getClass().getSimpleName());
		dataContainer.setActualValues();
		autoVerify.verifyValues(page,
				dataContainer.getMandatoryFields());
	}

	public void verifyAllFields(Page page) {
		IntDataContainer dataContainer = commonData.getContainer(page.getClass().getSimpleName());
		dataContainer.setActualValues();
		autoVerify.verifyValues(page,
				dataContainer.getFields());
	}

	public void verifyMandatoryFieldsFromAllContainers(Page page) {
		IntDataContainer dataContainer = commonData.getContainer(page.getClass().getSimpleName());
		dataContainer.setActualValuesForAllContainers();
		autoVerify.verifyValues(page,
				dataContainer.getMandatoryFieldsFromAllContainers());
	}

	public void verifyAllFieldsFromAllContainers(Page page) {
		IntDataContainer dataContainer = commonData.getContainer(page.getClass().getSimpleName());
		dataContainer.setActualValuesForAllContainers();
		autoVerify.verifyValues(page,
				dataContainer.getFieldsFromAllContainers());
	}

	public void verifyMandatoryFields(Page page, IntDataContainer dataContainer) {
		dataContainer.setActualValues();
		autoVerify.verifyValues(page,
				dataContainer.getMandatoryFields());
	}

	public void verifyAllFields(Page page, IntDataContainer dataContainer) {
		dataContainer.setActualValues();
		autoVerify.verifyValues(page,
				dataContainer.getFields());
	}

	public void verifyMandatoryFieldsFromAllContainers(Page page, IntDataContainer dataContainer) {
		dataContainer.setActualValuesForAllContainers();
		autoVerify.verifyValues(page,
				dataContainer.getMandatoryFieldsFromAllContainers());
	}

	public void verifyAllFieldsFromAllContainers(Page page, IntDataContainer dataContainer) {
		dataContainer.setActualValuesForAllContainers();
		autoVerify.verifyValues(page,
				dataContainer.getFieldsFromAllContainers());
	}

	public void verifyCustomFields(Page page, List<IntContainerField> fields) {
		autoVerify.verifyValues(page, fields);
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
		Wait(waitTimeInSeconds).until(expectation);
	}

	public Wait<WebDriver> Wait(int... waitTime) {
		int waitTimeInSeconds;
		if (waitTime.length > 0) {
			waitTimeInSeconds = waitTime[0];
		} else {
			waitTimeInSeconds = 5;
		}
		return new FluentWait<WebDriver>(getDriver())
				.withTimeout(waitTimeInSeconds, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(WebDriverException.class);
	}

	public void WaitForPageToLoad(int... waitTime) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
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
		if (!page.getUniqueElementInPage().isPresent()) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		assertThat("User is not in the page : "
				+ page.getClass().getSimpleName(), page
				.getUniqueElementInPage().isVisible());
	}

	public boolean isExist(Page page, int... waitTime) {
		WaitForPageToLoad(waitTime);
		return page.getUniqueElementInPage().isVisible();
	}
}
