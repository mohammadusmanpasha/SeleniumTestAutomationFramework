package com.ctl.it.qa.staf.crap;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

//TODO: Remove this crap
public class Utility extends HelperClass {

	public static WebDriver driver;
	public static Properties objectProp = new Properties();
	public static Properties configProp = new Properties();

	public static final String BROWSER_TYPE = "IE";
	public static final String URL = "https://vloda002.dev.qintra.com:9443/#/login";
	public static final String CTL_URL = "https://vloda002.dev.qintra.com:9443/appsec/basicLogin_en.html";
	public static final String CTL_URL_USERNAME = "slstst1";
	public static final String CTL_URL_PASSWORD = "2015Clink";
	
	
	
	public static final String POST_URL_TEXT = "/uws/#/login";

	public static String url;
	public static String ctlUrl;
	public static String browserType;
	public static String jacobDLLVersion;
	public static String ctlUrlUserName;
	public static String ctlUrlPassword;

	public String getbrowserType() {
		return browserType;
	}

	public Properties getConfigPropertyFile() {
		return configProp;
	}

	public WebDriver getDriver() throws Exception {
		return driver;
	}

	public String getjacobDLLVersion() {
		return jacobDLLVersion;
	}

	public Properties getObjectProp() {
		return objectProp;
	}

	public void Initialise() throws Exception {

		try {
			setConfigPropertyFile();

			setConfigParameters();

			setObjectPropertyFile();

			setDriverProperty();

			setDriver();

		} catch (Exception e) {

			System.out.println("Failed while Initialising the system" + e);
			throw e;
		}
	}

	public void setConfigParameters() {

		
		if ((configProp.getProperty("ipAddress").trim() != "") || (configProp.getProperty("ipAddress") != null)) {
			url = "https://" + configProp.getProperty("ipAddress") + ":" + configProp.getProperty("port") + POST_URL_TEXT;
		} else {
			url = URL;
		}
		
		if ((configProp.getProperty("browserType").trim() != "") || (configProp.getProperty("browserType") != null))
			browserType = configProp.getProperty("browserType");
		else
			browserType = BROWSER_TYPE;
		
		if ((configProp.getProperty("ctlUrl").trim() != "") || (configProp.getProperty("ctlUrl") != null))
			ctlUrl = configProp.getProperty("ctlUrl");
		else
			ctlUrl = CTL_URL;
		
		if ((configProp.getProperty("ctlUrlUserName").trim() != "") || (configProp.getProperty("ctlUrlUserName") != null))
			ctlUrlUserName = configProp.getProperty("ctlUrlUserName");
		else
			ctlUrlUserName = CTL_URL_USERNAME;
		
		if ((configProp.getProperty("ctlUrlPassword").trim() != "") || (configProp.getProperty("ctlUrlPassword") != null))
			ctlUrlPassword = configProp.getProperty("ctlUrlPassword");
		else
			ctlUrlPassword = CTL_URL_PASSWORD;

	}

	public void setConfigPropertyFile() throws Exception {

		try {
			configProp = setprop("config//AppConfig.Properties");
			;
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}

	public void setDefaultParameters() {
		browserType = BROWSER_TYPE;
	}

	public void setDriver() throws Exception {

		// if(driver != null)
		driver = setWebDriver(browserType);
	}

	public void setObjectPropertyFile() throws Exception {

		try {
			objectProp = setprop("objects//objects.Properties");

		}
		catch (IOException e) {}
		catch (ClassNotFoundException e) {}
	}

	public String getDate(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}
	
	public String getURL() {
		return url;
	}
	
	public String getCtlURL() {
		return ctlUrl;
	}
	
	public String getctlUrlUserName(){
		return ctlUrlUserName;
	}
	
	public String getctlUrlPassword(){
		return ctlUrlPassword;
	}
	
}
