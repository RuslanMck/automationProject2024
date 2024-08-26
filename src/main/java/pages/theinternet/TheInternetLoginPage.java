package pages.theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class TheInternetLoginPage extends BasePage {

    public TheInternetLoginPage (WebDriver driver){
        super(driver);
        pageUrl = "https://the-internet.herokuapp.com/login";
    }

    @FindBy(css = "h2")
    private WebElement pageTitle;

    @FindBy(css = "#login")
    private WebElement loginBlock;

    @FindBy(css = "#username")
    private WebElement loginInputField;

    @FindBy(css = "#password")
    private WebElement passwordInputField;

    @FindBy(css = "[type='submit']")
    private WebElement loginButton;

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public String getPageTitleText(){
        return pageTitle.getText();
    }

    public WebElement getLoginBlock() {
        return loginBlock;
    }

    public WebElement getLoginInputField() {
        return loginInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public void login(String email, String password){
        loginInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
    }

    public boolean contentIsDisplayed(){
        return pageTitle.isDisplayed() & loginBlock.isDisplayed();
    }

    public void navigate(){
        driver.get(pageUrl);
    }
}
