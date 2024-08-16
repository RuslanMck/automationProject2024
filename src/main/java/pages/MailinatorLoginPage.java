package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MailinatorLoginPage extends BasePage{

    private By searchInputLocator = By.cssSelector("#search");
    private By searchButtonLocator = By.xpath("//button[@value='Search for public inbox for free']");

    public MailinatorLoginPage(WebDriver driver){
        super(driver);
        pageUrl = "https://www.mailinator.com/";
    }

    public void navigate(){
        driver.get(pageUrl);
    }

    public void SearchForLetter(){
        WebElement searchButton = driver.findElement(searchButtonLocator);

        searchButton.click();
    }

    public void InputTheEmail(String email){
        WebElement searchInput = driver.findElement(searchInputLocator);
        searchInput.click();
        searchInput.sendKeys(email);
    }
}
