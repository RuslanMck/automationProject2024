package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import testdata.User;

import java.time.Duration;

public class UkrnetLoginPage extends BasePage{

    private By loginFieldLocator = By.cssSelector("[name='login']");
    private By passwordFieldLocator = By.cssSelector("[name='password']");
    private By submitButtonLocator = By.cssSelector("[type='submit']");

    public UkrnetLoginPage(WebDriver driver){
        super(driver);
        pageUrl = "https://mail.ukr.net/";

    }

    public void navigate(){
        driver.get(pageUrl);
    }

    public void login(User user){
        driver.findElement(loginFieldLocator).sendKeys(user.getLogin());
        driver.findElement(passwordFieldLocator).sendKeys(user.getPassword());
        driver.findElement(submitButtonLocator).click();


    }


    }


