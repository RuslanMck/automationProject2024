import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TestNGDemo {

    private String baseUrl = "https://soliterata.com/practice-form/";

    @Test
    public void inputTests(){
        System.setProperty("selenium.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get(baseUrl);

        By cookieLocator = By.xpath("//div[@data-elementor-id=\"4540\"]");
        WebElement cookieBlock = driver.findElement(cookieLocator);

        By cookieAcceptLocator = By.xpath("//*[text()='Accept']");
        WebElement acceptButton = driver.findElement(cookieAcceptLocator);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        if(cookieBlock.isDisplayed()){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Accept']")));
            acceptButton.click();
        }

        By inputsLocators = By.xpath("//input[@type='text']");
        List<WebElement> inputFields = driver.findElements(inputsLocators);

        inputFields.get(1).sendKeys("My sent key");

        //1:18:15 / 2:24:07
    }
}
