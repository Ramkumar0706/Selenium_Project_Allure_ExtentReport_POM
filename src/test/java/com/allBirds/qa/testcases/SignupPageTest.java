package com.allBirds.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.allBirds.qa.base.TestBase;
import com.allBirds.qa.listener.CustomListener;
import com.allBirds.qa.pages.LoginPage;
import com.allBirds.qa.pages.SignupPage;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners(CustomListener.class)
public class SignupPageTest extends TestBase {
	
	SignupPage page;

	public SignupPageTest() {
		super();
	}
	
	@BeforeMethod
	@Step("Setting up browser and initializing the SignupPage object.")
	void setup() {
		init();
		page=new SignupPage();
	}
	
	@Test(priority = 1)
	@Description("Test case to verify the signup functionality with valid user details.")
	@Severity(SeverityLevel.CRITICAL)
	@Feature("Signup Feature")
	@Story("As a new user, I want to register to the platform using valid credentials.")
	@Step("Performing signup with first name, last name, email, and password.")
	void signupTest() {
		page.signUp(data.getProperty("firstName"), data.getProperty("lastName"), data.getProperty("email"), data.getProperty("password"), data.getProperty("password"));
		
	}
	
	@Test(priority = 2)
	@Description("Test case to verify that all signup fields are displayed and enabled.")
	@Severity(SeverityLevel.MINOR)
	@Feature("Signup Fields Validation")
	@Story("As a new user, I want to ensure that the signup form fields are visible and functional.")
	@Step("Validating all signup fields.")
	void validateSignupFieldsTest() {
		Assert.assertTrue(page.validateFields(), "Some signup fields are not displayed or enabled.");
	}

	@Test(priority = 3)
	@Description("Test case to verify the visibility and functionality of the Register button.")
	@Severity(SeverityLevel.MINOR)
	@Feature("Register Button Validation")
	@Story("As a new user, I want to ensure that the Register button is visible and functional.")
	@Step("Validating the Register button.")
	void validateRegisterButtonTest() {
		Assert.assertTrue(page.validateSignUpButton(), "Register button is not displayed or enabled.");
	}

	@Test(priority = 4)
	@Description("Test case to verify the signup page title.")
	@Severity(SeverityLevel.MINOR)
	@Feature("Signup Page Title Validation")
	@Story("As a new user, I want to ensure that the signup page has the correct title.")
	@Step("Validating signup page title.")
	void validateSignupPageTitleTest() {
		String actualTitle = page.validateSignupPageTitle();
		Assert.assertEquals(actualTitle, "Login or Create an Account - Allbirds", "Signup page title does not match.");
	}
	
	@AfterMethod
	@Step("Closing the browser after signup test execution.")
	void quit() {
		driver.quit();
	}
	
	

}
