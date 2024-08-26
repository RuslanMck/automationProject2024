package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UkrnetHomePage extends BasePage{

    @FindBy(css = ".button.primary")
    private WebElement sendLetterButton;

    @FindBy(css = "[name='toFieldInput']")
    private WebElement toInput;

    @FindBy(css = "[name='subject']")
    private WebElement subjectInput;

    @FindBy(css = ".screen__head .send")
    private WebElement sendButton;

    @FindBy(css = "iframe#mce_0_ifr")
    private WebElement iFrameBody;

    @FindBy(css = "#tinymce")
    private WebElement letterBody;

    @FindBy(css = ".sendmsg__ads-ready")
    private WebElement letterIsSent;


    public UkrnetHomePage(WebDriver driver){
        super(driver);
        pageUrl = "https://mail.ukr.net/desktop";
    }

    public void openNewLetter(){
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofMillis(200))
                                .ignoring(NoClassDefFoundError.class)
                                        .until(ExpectedConditions.visibilityOf(sendLetterButton));
        sendLetterButton.click();
    }

    public void writeLetter(String to, String subject, String body){
        toInput.sendKeys(to);
        subjectInput.sendKeys(subject);
        try {
            driver.switchTo().frame(iFrameBody);
            letterBody.sendKeys(body);
        } finally {
            driver.switchTo().parentFrame();
        }

    }

    public void sendLetter(){
        sendButton.click();
    }

    public String getTextLetterIsSent(){
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sendmsg__ads-ready")));
        return letterIsSent.getText().substring(0, 18);
    }

}
