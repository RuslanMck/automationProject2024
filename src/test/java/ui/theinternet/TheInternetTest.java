package ui.theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.theinternet.TheInternetLoginPage;
import ui.BaseTest;

import java.util.regex.Pattern;

public class TheInternetTest extends BaseTest {

//    private WebDriver driver;
    private TheInternetLoginPage theInternetLoginPage;

//    @BeforeClass
//    private void testSetup(){
//        System.setProperty("selenium.chrome.driver", "path/to/chromedriver");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    }

    @Test
    public void pageIsOpened(){
        theInternetLoginPage = new TheInternetLoginPage(driver);
        theInternetLoginPage.navigate();
        Assert.assertTrue(theInternetLoginPage.contentIsDisplayed());
        Assert.assertEquals(theInternetLoginPage.getPageTitleText(), "Login Page");

    }

    @Test
    public void loginWithValidCredentials(){
        theInternetLoginPage = new TheInternetLoginPage(driver);
        theInternetLoginPage.navigate();
        theInternetLoginPage.login("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(theInternetLoginPage.isLoggedIn());
    }

    @Test
    public void loginWithInvalidUsername(){
        theInternetLoginPage = new TheInternetLoginPage(driver);
        theInternetLoginPage.navigate();
        theInternetLoginPage.login("qwe", "SuperSecretPassword!");
        Assert.assertEquals(theInternetLoginPage.getErrorMessage(), "Your username is invalid!");
    }

    @Test
    public void loginWithInvalidPassword(){
        theInternetLoginPage = new TheInternetLoginPage(driver);
        theInternetLoginPage.navigate();
        theInternetLoginPage.login("tomsmith", "qweasd");
        Assert.assertEquals(theInternetLoginPage.getErrorMessage(), "Your password is invalid!");
    }
}
