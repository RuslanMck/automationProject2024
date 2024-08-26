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

    @FindBy(xpath = "//div[contains(@class, 'success')]")
    private WebElement loginConfirmationPopup;

    @FindBy(xpath = "//div[contains(@class, 'error')]")
    private WebElement loginErrorPopup;

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

    public void login(String login, String password){
        loginInputField.sendKeys(login);
        passwordInputField.sendKeys(password);
        loginButton.click();
    }

    public boolean contentIsDisplayed(){
        return pageTitle.isDisplayed() & loginBlock.isDisplayed();
    }

    public boolean isLoggedIn(){
        return loginConfirmationPopup.isDisplayed();
    }

    public void navigate(){
        driver.get(pageUrl);
    }

    public WebElement getLoginErrorPopup() {
        return loginErrorPopup;
    }

    public String getErrorMessage(){
        return waitAndReturnText(loginErrorPopup).replace("×", "").trim();
    }

}
