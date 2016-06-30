package com.ctl.it.qa.staf.crap;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

//TODO: Remove this crap
public class UIActions extends Objects {

	public void checkDialog_isDisplayed(String dialogName) throws Exception {
		//Dialog(dialogName).isDisplayed();

	}

	public void click(WebElement element) throws Exception {

		try {
			if (element.isDisplayed() && element.isEnabled() && element != null) {
				element.click();
			} else {
				System.out.println("WebElement" + element + " is disabled or not displayed");
			}
		} catch (Exception e) {
			throw e;
		}

	}
	
	public void doubleClick(WebElement element) throws Exception {

		Actions act = new Actions(util.getDriver());

		if (element.isDisplayed() && element.isEnabled())
			act.doubleClick(element).build().perform();

	}

	public void dragAndDrop(WebElement srcEle, WebElement targetEle) throws Exception {
		Actions dragDrop = new Actions(util.getDriver());
		dragDrop.dragAndDrop(srcEle, targetEle).build().perform();
	}

	public void elementDoesntExists(WebElement element) throws Exception {

		int counter = 1;
		try {
			while(element.isDisplayed()){
				Thread.sleep(1000);
				counter++;
				if(counter == 100)
					break;
			}
		} catch (StaleElementReferenceException e) {
			if(!(counter > 1))
			{
				System.out.println("element still exists");
				throw e;
			}
		}

		Thread.sleep(3000);
	}

	public boolean elementExists(WebElement element) {

		return element.isDisplayed();
	} 

	public void navigateToCtlURL() throws Exception {
		util.getDriver().get(util.getCtlURL());
	}
	
	public void navigateToURL() throws Exception {
		//util.getDriver().get(util.getURL());
		util.getDriver().navigate().to(util.getURL());
	}

	public void rightClick(WebElement element) throws Exception {

		Actions act = new Actions(util.getDriver());

		Thread.sleep(2000);
		act.contextClick(element).build().perform();
	}

	public WebElement scrollIntoView(WebElement element) throws Exception {

		((JavascriptExecutor) util.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);

		return element;
	}

	public void selectComboBoxValue(String dropDownName, String dropDownValue) throws Exception {

		String xpath = util.getObjectProp().getProperty("ComboBox").replaceAll("comboBoxLabel", dropDownName);
		
		int attempts = 0;
		
		do {
			try {
				
				click(getElement(xpath + "//button"));
				
				click(getElement(xpath + "//li[text()='"+dropDownValue+"']"));
				
				break;
			}
			catch (Exception e){
				
				try {
					Select selectByValue = new Select(getElement(xpath + "//select"));
					selectByValue.selectByValue(dropDownValue);
					break;
					
				} catch (StaleElementReferenceException e1) {
					attempts++;
				}
				
			}
			
		}while (attempts < 5);

	}

	public void selectComboBoxValue(String dropDownName, String dropDownValue, int index) throws Exception {

		String xpath = util.getObjectProp().getProperty("ComboBox").replaceAll("comboBoxLabel", dropDownName);
		
		int attempts = 0;
		
		do {
			try {
				
				click(getElement(xpath + "//button"));
				
				click(getElement(xpath + "//li[text()='"+dropDownValue+"']"));
				
				break;
			}
			catch (Exception e){
				
				try {
					Select selectObj = new Select(getElements(xpath + "//select").get(index - 1));
					selectObj.selectByVisibleText(dropDownValue);
					break;
					
				} catch (StaleElementReferenceException e1) {
					attempts++;
				}
				
			}
			
		}while (attempts < 5);

	}
	
	public void selectMenu(String menuName) throws Exception {

		//click(Menu(menuName));

	}
	
	public void selectTableValue(String colValue,String colName) throws Exception {
		
		click(Table(colName, colValue));
		
	}

	public void setText(WebElement element, String value) throws Exception {

		try {
			if (element.isDisplayed() && element.isEnabled() && element != null) {
				element.clear();

				if (value.equalsIgnoreCase("currenttimeinmillisecs"))
					element.sendKeys(String.valueOf(System.currentTimeMillis()));

				else if(value.equalsIgnoreCase("currentDate"))
					element.sendKeys(new SimpleDateFormat("MM/dd/YYYY").format(new Date()));

				else
					element.sendKeys(value);
			} else {
				System.out.println("Text Box is disabled or not displayed");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public void switchToDefaultFrame() throws Exception {

		util.getDriver().switchTo().defaultContent();

	}

	public void switchToFrame(String frameNumber) throws Exception {

		util.getDriver().switchTo().frame(Frame(frameNumber));

	}

	public void tearDown() throws Exception {

		util.getDriver().quit();
	}

	public void wait_for_dialog_disappearance(String dialogName) throws Exception {

		//elementDoesntExists(Dialog(dialogName));
	}

	public void waitForGivenTime(int waitTime) throws Exception {

		Thread.sleep(waitTime * 1000);
	}

}
