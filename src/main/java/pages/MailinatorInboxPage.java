package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waiters.LoadingWaiter;

import java.time.Duration;
import java.util.List;

public class MailinatorInboxPage extends BasePage{

    @FindBy(xpath = "//h4[normalize-space(text())='Public Messages']")
    private WebElement pageTitleLocator;

    @FindBy(css = "[id*='row_mck12q']")
    private List<WebElement> messagesListLocator;

    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

    public MailinatorInboxPage(WebDriver driver){
        super(driver);
        pageUrl = "https://www.mailinator.com/v4/public/inboxes.jsp?to=mck12q";
    }

    public String getPageTitle(){
        LoadingWaiter.waitUntilSubjectIsDisplayed(pageTitleLocator, "Public Messages");
        return pageTitleLocator.getText();
    }

    public void openMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id*='row_mck12q']")));

        System.out.println(messagesListLocator.size());
        messagesListLocator.get(0).click();
    }



    public void waitUntilJsIsReady(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor)
                wd).executeScript("return document.readyState").equals("complete"));
    }


}
