package ukrnet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import testdata.User;

public class UkrnetTest {

    private WebDriver driver;
    private String email = "mck12q@mailinator.com";
    private String subject = "tets";
    private String emailText = "Tetttssss";

    @BeforeClass
    public void testSetUp(){
        System.setProperty("selenium.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void SendEmailToMailinator() {
        User user = new User("ruslan1test@ukr.net","123qweQWE");
        UkrnetLoginPage ukrnetLoginPage = new UkrnetLoginPage(driver);
        ukrnetLoginPage.navigate();
        ukrnetLoginPage.login(user);

        UkrnetHomePage ukrnetHomePage = new UkrnetHomePage(driver);
        ukrnetHomePage.waitUntilLoaded();

        ukrnetHomePage.openNewLetter();
        ukrnetHomePage.writeLetter(email, subject, emailText);
        ukrnetHomePage.sendLetter();
        System.out.println("String" + ukrnetHomePage.getTextLetterIsSent());

        Assert.assertEquals(ukrnetHomePage.getTextLetterIsSent(), "Ваш лист надіслано");

        MailinatorLoginPage mailinatorLoginPage = new MailinatorLoginPage(driver);
        mailinatorLoginPage.navigate();
        mailinatorLoginPage.InputTheEmail(email);
        mailinatorLoginPage.SearchForLetter();

        MailinatorInboxPage mailinatorInboxPage = new MailinatorInboxPage(driver);
        Assert.assertEquals(mailinatorInboxPage.getPageTitle(), "Public Messages");

        mailinatorInboxPage.waitUntilJsIsReady();
        mailinatorInboxPage.openMessage();

        MailinatorMessagPage mailinatorMessagPage = new MailinatorMessagPage(driver);

        Assert.assertEquals(mailinatorMessagPage.getEmailSubject(), subject);
        Assert.assertEquals(mailinatorMessagPage.getEmailFrom(), "ruslan1test@ukr.net");
        Assert.assertEquals(mailinatorMessagPage.getEmailText(), emailText);



    }
}
