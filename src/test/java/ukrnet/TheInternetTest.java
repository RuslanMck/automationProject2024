package ukrnet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.theinternet.TheInternetLoginPage;

public class TheInternetTest {

    private WebDriver driver;
    private TheInternetLoginPage theInternetLoginPage;

    @BeforeClass
    private void testSetup(){
        System.setProperty("selenium.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void pageIsOpened(){
        theInternetLoginPage = new TheInternetLoginPage(driver);

        theInternetLoginPage.navigate();
        Assert.assertTrue(theInternetLoginPage.contentIsDisplayed());
        Assert.assertEquals(theInternetLoginPage.getPageTitleText(), "Login Page");
    }
}
