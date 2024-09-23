package com.allBirds.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.allBirds.qa.base.TestBase;
import com.allBirds.qa.listener.CustomListener;
import com.allBirds.qa.pages.HomePage;
import com.allBirds.qa.pages.LoginPage;
import com.allBirds.qa.pages.SearchPage;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners(CustomListener.class)
public class SearchPageTest extends TestBase {

    HomePage homePage;
    LoginPage login;
    SearchPage search;

    public SearchPageTest() {
        super();
    }

    @BeforeMethod
    @Step("Setting up browser, initializing page objects, and logging in.")
    void setup() {
        init();
        login = new LoginPage();
        homePage = login.login(data.getProperty("email"), data.getProperty("password"));
        search =homePage.searchItems();
    }

    @Test(priority = 1)
    @Description("Test case to verify the search functionality by searching for 'shirts'.")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Search Feature")
    @Story("As a user, I want to search for products in the search bar.")
    @Step("Searching for 'shirts' on the website")
    void searchContents() {
        search.searchContent("shirts");
      Assert.assertTrue(search.validateSearchResults(), "Search results are not displayed.");
    }

    @Test(priority = 3)
    @Description("Test case to verify the search field visibility and functionality.")
    @Severity(SeverityLevel.MINOR)
    @Feature("Search Field Validation")
    @Story("As a user, I want to ensure that the search field is visible and functional.")
    @Step("Validating the search field")
    void validateSearchFieldTest() {
    	 search.searchContent("shoes");
        Assert.assertTrue(search.validateSearchField(), "Search field is not displayed or enabled.");
    }

    @Test(priority = 4)
    @Description("Test case to verify the search page title after searching for content.")
    @Severity(SeverityLevel.MINOR)
    @Feature("Search Page Title Validation")
    @Story("As a user, I want to validate the search page title after performing a search.")
    @Step("Validating search page title after searching for 'shoes'")
    void validateSearchPageTitleTest() {
        search.searchContent("shoes");
        String actualTitle = search.validateSearchPageTitle();
        Assert.assertEquals(actualTitle, "", "Search page title does not match.");
    }

    

    @AfterMethod
    @Step("Closing the browser after test execution.")
    void shutDown() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
