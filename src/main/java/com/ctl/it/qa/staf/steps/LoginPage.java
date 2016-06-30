package com.ctl.it.qa.staf.steps;

import com.ctl.it.qa.staf.crap.UIActions;

import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;
import net.thucydides.core.annotations.Step;

//TODO: Need to change this to use serenity locators.
@NamedUrls(
		{
	@NamedUrl(name="url1",url="https://vloda002.dev.qintra.com:9443/#/login"),
	@NamedUrl(name="url2",url="https://vloda002.dev.qintra.com:9443/appsec/basicLogin_en.html")
})
public class LoginPage extends UIActions {

	@Step("Go to Login Page")
	public void goToLoginPage() throws Exception {
		navigateToURL();
	}

	@Step("Login to Ctl")
	public void loginToCtl() throws Exception {
		navigateToCtlURL();
		String xpath = "//td//p[contains(text(),'TextBoxLabel')]//parent::td//following-sibling::td//input[@type='text' or @type='password']";
		setText(getElement(xpath.replaceAll("TextBoxLabel", "User ID")), util.getctlUrlUserName());
		setText(getElement(xpath.replaceAll("TextBoxLabel", "Password")), util.getctlUrlPassword());
		String btnXpath = "//td//input[@type = 'image' and @src = 'images/btn_go.gif']";
		click(getElement(btnXpath));
		Thread.sleep(5000);
	}

}
