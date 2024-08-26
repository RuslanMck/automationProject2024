package ui.theinternet;


import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.theinternet.TheInternetLoginPage;
import ui.BaseTest;
import ui.dataproviders.DataProviders;
;

public class TheInternetTest extends BaseTest {

    private TheInternetLoginPage theInternetLoginPage;


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

    @Test(dataProvider = "userCredentialsDataProvider", dataProviderClass = DataProviders.class)
    public void loginWithInvalidCredentials(String login, String password, String message){
        theInternetLoginPage = new TheInternetLoginPage(driver);
        theInternetLoginPage.navigate();
        theInternetLoginPage.login(login, password);
        Assert.assertEquals(theInternetLoginPage.getErrorMessage(), message);

//        theInternetLoginPage.login("tomsmith", "qweasd");
//        Assert.assertEquals(theInternetLoginPage.getErrorMessage(), "Your password is invalid!");
    }

}
