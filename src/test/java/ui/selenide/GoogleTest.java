package ui.selenide;

import com.codeborne.selenide.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.google.GoogleHomePage;
import pages.google.SelenideBlogPage;
import pages.google.SelenideHomePage;

public class GoogleTest {

    @BeforeClass
    public void setUp(){
        Configuration.holdBrowserOpen = true;
        Configuration.savePageSource = false;
        Configuration.timeout = 3000;
        Configuration.browserSize = "1920x1080";
        Selenide.open("https://google.com");

    }

    @Test
    public void userShouldSearch(){
        GoogleHomePage googleHomePage = new GoogleHomePage();
        SelenideHomePage selenideHomePage = new SelenideHomePage();
        SelenideBlogPage selenideBlogPage = new SelenideBlogPage();

        googleHomePage
                .waitUntilAcceptCookieButtonIsVisible()
                .clickCookieAcceptButton()
                .waitUntilSearchInputFieldIsVisible()
                .inputSearchKey("Selenide")
                .pressEnter();

        googleHomePage
                .waitUntilResultsAreVisible()
                .verifyNumberOfResults(6)
                .clickFirstResult();

        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "https://selenide.org/");

        selenideHomePage
                .waitUntilHeaderIsVisible()
                .verifyHeaderText("Selenide Supports Ukraine \uD83C\uDDFA\uD83C\uDDE6")
                .clickBlogLink();

        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "https://selenide.org/blog.html");

        selenideBlogPage
                .waitUntilHeaderIsVisible()
                .checkHeaderText("Selenide blog");

    }
}
