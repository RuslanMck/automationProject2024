package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UkrnetHomePage extends BasePage{

    private By sendLetterButton = By.cssSelector(".button.primary");
    private By toInput = By.cssSelector("[name='toFieldInput']");
    private By subjectInput = By.cssSelector("[name='subject']");
    private By sendButton = By.cssSelector(".screen__head .send");
    private By iFrameBody = By.cssSelector("iframe#mce_0_ifr");
    private By letterBody = By.cssSelector("#tinymce");


    public UkrnetHomePage(WebDriver driver){
        super(driver);
        pageUrl = "https://mail.ukr.net/desktop";
    }

    public void openNewLetter(){
        driver.findElement(sendLetterButton).click();
    }

    public void writeLetter(String to, String subject, String body){
        driver.findElement(toInput).sendKeys(to);
        driver.findElement(subjectInput).sendKeys(subject);
        try {
            driver.switchTo().frame(driver.findElement(iFrameBody));
            driver.findElement(letterBody).sendKeys(body);
        } finally {
            driver.switchTo().parentFrame();
        }

    }

    public void sendLetter(){
        driver.findElement(sendButton).click();
    }

}
