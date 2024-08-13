package ukrnet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.UkrnetHomePage;
import pages.UkrnetLoginPage;
import testdata.User;

public class UkrnetTest {

    private WebDriver driver;

    @BeforeClass
    public void testSetUp(){
        System.setProperty("selenium.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void SendEmailToMailinator() throws InterruptedException {
        User user = new User("ruslan1test@ukr.net","123qweQWE");
        UkrnetLoginPage ukrnetLoginPage = new UkrnetLoginPage(driver);
        ukrnetLoginPage.navigate();
        ukrnetLoginPage.login(user);

        UkrnetHomePage ukrnetHomePage = new UkrnetHomePage(driver);
        ukrnetHomePage.waitUntilLoaded();

        ukrnetHomePage.openNewLetter();
        ukrnetHomePage.writeLetter("mck12q@mailinator.com", "tets", "Tetttssss");
        ukrnetHomePage.sendLetter();

    }
}
