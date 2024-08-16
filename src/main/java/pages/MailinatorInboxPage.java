package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MailinatorInboxPage extends BasePage{

    private By pageTitleLocator = By.xpath("//h4[normalize-space(text())='Public Messages']");
    private By messagesListLocator = By.cssSelector("[id*='row_mck12q']");


    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

    public MailinatorInboxPage(WebDriver driver){
        super(driver);
        pageUrl = "https://www.mailinator.com/v4/public/inboxes.jsp?to=mck12q";
    }

    public String getPageTitle(){
        WebElement PageTitle = driver.findElement(pageTitleLocator);
        return PageTitle.getText();
    }

    public void openMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(messagesListLocator));

        List<WebElement> messagesList = driver.findElements(messagesListLocator);
        System.out.println(messagesList.size());
        messagesList.get(0).click();
    }



    public void waitUntilJsIsReady(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor)
                wd).executeScript("return document.readyState").equals("complete"));
    }


}
