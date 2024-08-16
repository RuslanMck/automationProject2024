package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class MailinatorMessagPage extends BasePage {

    private By emailSubjectLocator = By.xpath("//div[@class='fw-700 fz-20 ff-futura-demi']//following-sibling::div");
    private By emailFromLocator = By.xpath("//div[normalize-space(text())='From']//following-sibling::div");
    private By iFrameLocator = By.cssSelector("#html_msg_body");
    private By emailTextLoctor = By.cssSelector(".xfmc1");

    public MailinatorMessagPage(WebDriver driver) {
        super(driver);
    }

    public String getEmailSubject() {
        waitUntilSubjectIsDisplayed();

        System.out.println(driver.findElement(emailSubjectLocator).getText());
        return driver.findElement(emailSubjectLocator).getText();
    }

    public String getEmailFrom() {
        return driver.findElement(emailFromLocator).getText();
    }

    public String getEmailText() {
        try {
            driver.switchTo().frame(driver.findElement(iFrameLocator));
            return driver.findElement(emailTextLoctor).getText();
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
                if (driver.findElement(emailSubjectLocator).getText().equals("tets")) {
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


