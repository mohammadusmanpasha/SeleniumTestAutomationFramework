package com.ctl.it.qa.staf.stepdefs;

import com.ctl.it.qa.staf.crap.UIActions;
import com.ctl.it.qa.staf.steps.LoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginStepDefs {
	
	private LoginPage loginPage;
	private UIActions uiActions;
	
	@Given("^I am on the login screen$")
	public void i_am_on_the_login_screen() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		this.loginPage.goToLoginPage();
	}
	
	@Then("^I Enter the username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void login_with_username_password(String userName, String password) throws Throwable {
		
		try {
			this.uiActions.setText(this.loginPage.InputTextBox("Username"), userName);
			this.loginPage.setText(this.loginPage.InputTextBox("Password"), password);
		} catch (Exception e) {
			System.out.println("Failed while entering username/password");
		}
	}
	
	@Then("^wait for Login to be successful$")
	public void wait_For_Login_Successful()	throws Throwable {

	}
	

}
