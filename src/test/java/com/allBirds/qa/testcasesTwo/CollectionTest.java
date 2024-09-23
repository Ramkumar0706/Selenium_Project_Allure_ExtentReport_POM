package com.allBirds.qa.testcasesTwo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.allBirds.qa.base.TestBase;
import com.allBirds.qa.pages.CollectionsPage;
import com.allBirds.qa.pages.HomePage;
import com.allBirds.qa.pages.LoginPage;

public class CollectionTest extends TestBase{
	
	HomePage home;
	LoginPage login;
	CollectionsPage cp;
	public  CollectionTest() {
		super();	
		}
	
	@BeforeMethod
	void setup() {
		init();
		login=new LoginPage();
		
	}
	@Test
	void basicTest() {
		HomePage home = login.login(data.getProperty("email"), data.getProperty("password"));
		driver.navigate().to("https://www.allbirds.com/");
		CollectionsPage cp = home.viewMensProduct("Active Shoes");
		cp.productPage();
	}

}
