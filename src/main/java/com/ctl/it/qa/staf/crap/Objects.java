package com.ctl.it.qa.staf.crap;

import java.util.List;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.pages.PageObject;

//TODO: Remove this crap
public class Objects extends PageObject {


	protected Utility util = new Utility();
	
	public WebElement Button(String buttonName) throws Exception {

		return HelperClass.findWebElement(util.getObjectProp().getProperty(HelperClass.getCurrentMethodName()).replaceAll("buttonName", buttonName), util.getDriver());
	}
	
	public WebElement CheckBox(String checkboxLabel) throws Exception {

		return HelperClass.findWebElement(util.getObjectProp().getProperty(HelperClass.getCurrentMethodName()).replaceAll("checkboxLabel", checkboxLabel), util.getDriver());
	}

	public WebElement ComboBox(String dropDownName) throws Exception {

		return HelperClass.findWebElement(util.getObjectProp().getProperty(HelperClass.getCurrentMethodName()).replaceAll("dropDownName", dropDownName), util.getDriver());
	}

	public WebElement Element(String elementText) throws Exception {

		return HelperClass.findWebElement(util.getObjectProp().getProperty(HelperClass.getCurrentMethodName()).replaceAll("elementText", elementText), util.getDriver());
	}

	public WebElement Frame(String frameNumber) throws Exception {

		return HelperClass.findWebElement(util.getObjectProp().getProperty(HelperClass.getCurrentMethodName()).replaceAll("frameNumber", frameNumber), util.getDriver());
	}

	public WebElement getElement(String xpath) throws Exception {

		return HelperClass.findWebElement(xpath, util.getDriver());
	}

	public WebElement InputTextBox(String inputBoxLabel) throws Exception {

		return HelperClass.findWebElement(util.getObjectProp().getProperty(HelperClass.getCurrentMethodName()).replaceAll("inputBoxLabel", inputBoxLabel), util.getDriver());
	}

	public WebElement Label(String labelName) throws Exception {

		return HelperClass.findWebElement(util.getObjectProp().getProperty(HelperClass.getCurrentMethodName()).replaceAll("labelName", labelName), util.getDriver());
	}

	public List<WebElement> getElements(String xpath) throws Exception {

		return HelperClass.findWebElements(xpath, util.getDriver());
	}

	public WebElement Panel(String panelText) throws Exception {

		return HelperClass.findWebElement(util.getObjectProp().getProperty(HelperClass.getCurrentMethodName()).replaceAll("panelText", panelText), util.getDriver());
	}

	public WebElement Radio(String radioLabel) throws Exception {

		return HelperClass.findWebElement(util.getObjectProp().getProperty(HelperClass.getCurrentMethodName()).replaceAll("radioLabel", radioLabel), util.getDriver());
	}

	public WebElement Table(String colName, String colValue) throws Exception {

		String xpath = util.getObjectProp().getProperty(HelperClass.getCurrentMethodName());
		
		xpath = xpath.replaceAll("colName", colName).replaceAll("colValue", colValue);
		
		return HelperClass.findWebElement(xpath, util.getDriver());
	}

}
