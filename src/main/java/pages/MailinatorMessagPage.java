package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;


public class MailinatorMessagPage extends BasePage {

    @FindBy(xpath = "//div[@class='fw-700 fz-20 ff-futura-demi']//following-sibling::div")
//    @FindBy(xpath = "//div[contains(normalize-space(text()), 'Public Message')]/following-sibling::div")
    private WebElement emailSubjectLocator;

    @FindBy(xpath = "//div[normalize-space(text())='From']//following-sibling::div")
    private WebElement emailFromLocator;

    @FindBy(css = "#html_msg_body")
    private WebElement iFrameLocator;

    @FindBy(css = ".xfmc1")
    private WebElement emailTextLoctor;

    public MailinatorMessagPage(WebDriver driver) {
        super(driver);
    }

    public String getEmailSubject() {

        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .until(ExpectedConditions
                        .textToBe(By.xpath("//div[contains(normalize-space(text()), " +
                                "'Public Message')]/following-sibling::div"), "tets"));

//        waitUntilSubjectIsDisplayed();

        System.out.println(emailSubjectLocator.getText());
        return emailSubjectLocator.getText();
    }

    public String getEmailFrom() {
        return emailFromLocator.getText();
    }

    public String getEmailText() {
        try {
            driver.switchTo().frame(iFrameLocator);
            return emailTextLoctor.getText();
        } finally {
            driver.switchTo().parentFrame();

        }
    }

    /**
     * This is custom waiter that will execute Thread.sleep() method is the element does not contain needed text.
     * The .getText().equals("tets") can be changed by .isDisplayed().
     * <p>
     * Originally we want to use only
     * <pre><code>
     * catch (NoSuchElementException e) {
     *      hread.sleep(500);
     *      }
     * </code></pre>
     * But we need to wrap the Thread.sleep() with try-catch
     */
    public void waitUntilSubjectIsDisplayed() {
        for (int i = 0; i < 20; i++) {
            System.out.println(i);

            try {
                if (emailSubjectLocator.getText().equals("tets")) {
                    return;
                }
            } catch (NoSuchElementException e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }
}


