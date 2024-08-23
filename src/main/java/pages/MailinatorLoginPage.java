package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailinatorLoginPage extends BasePage{

    @FindBy(css = "#search")
    private WebElement searchInputLocator;

    @FindBy(xpath = "//button[@value='Search for public inbox for free']")
    private WebElement searchButtonLocator;

    public MailinatorLoginPage(WebDriver driver){
        super(driver);
        pageUrl = "https://www.mailinator.com/";
    }

    public void navigate(){
        driver.get(pageUrl);
    }

    public void SearchForLetter(){
        searchButtonLocator.click();
    }

    public void InputTheEmail(String email){
        searchInputLocator.click();
        searchInputLocator.sendKeys(email);
    }
}
