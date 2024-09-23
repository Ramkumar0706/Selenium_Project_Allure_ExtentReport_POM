package com.allBirds.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.allBirds.qa.base.TestBase;
import com.allBirds.qa.listener.CustomListener;
import com.allBirds.qa.pages.CartPage;
import com.allBirds.qa.pages.CollectionsPage;
import com.allBirds.qa.pages.HomePage;
import com.allBirds.qa.pages.LoginPage;
import com.allBirds.qa.pages.ProductPage;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners(CustomListener.class)
public class CartPageTest extends TestBase {
	HomePage homePage;
	LoginPage login;
	CollectionsPage collectionsPage;
	ProductPage productPage;
	@BeforeMethod
	void setup() {
		init();
		homePage=new HomePage();
	}

	@Test(priority = 1)
	@Description("Test case is user to select the product and add cart after that user is able to view the cart items.")
	@Severity(SeverityLevel.NORMAL)
	@Feature("View Cart")
	@Story("As a user,add product to the cart after user is view the product in the cart items")
	@Step("Viewing the Cart Page")
	void viewCartTest() {
		login =homePage.viewAccount();
		homePage = login.login(data.getProperty("email"), data.getProperty("password"));
		driver.navigate().to("https://www.allbirds.com/");
		collectionsPage = homePage.viewMensProduct("Active Shoes");
		productPage = collectionsPage.productPage();
		homePage = productPage.selectProduct();
		driver.navigate().to("https://www.allbirds.com/");
		homePage.viewCart();

	}

	@AfterMethod
	void tearDown() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}



}
