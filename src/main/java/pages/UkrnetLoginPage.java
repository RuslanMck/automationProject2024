package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testdata.User;

public class UkrnetLoginPage extends BasePage {

    @FindBy(css = "[name='login']")
    private WebElement loginFieldLocator;

    @FindBy(css = "[name='password']")
    private WebElement passwordFieldLocator;

    @FindBy(css = "[type='submit']")
    private WebElement submitButtonLocator;

    public UkrnetLoginPage(WebDriver driver) {
        super(driver);
        pageUrl = "https://mail.ukr.net/";

    }

    public void navigate() {
        driver.get(pageUrl);
    }

    public void login(User user) {
        loginFieldLocator.sendKeys(user.getLogin());
        passwordFieldLocator.sendKeys(user.getPassword());
        submitButtonLocator.click();

    }
}


