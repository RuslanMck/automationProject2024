import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {

        //https://soliterata.com/practice-form/

        String firstName = "John";

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String baseUrl = "https://soliterata.com/practice-form/";

        driver.manage().window().maximize();
        driver.get(baseUrl);

        By inputFirstNameLocator = By.xpath("//input[@id='form-field-name']");
        WebElement inputFirstNameElement = driver.findElement(inputFirstNameLocator);

        By cookieBlockLocator = By.xpath("//div[@data-elementor-id='4540']");
        WebElement cookieBlockElement = driver.findElement(cookieBlockLocator);

        By cookieAcceptButtonLocator = By.xpath("//*[@id=\"elementor-popup-modal-4540\"]/div/div[2]/div/section/div/div[2]/div/div/div/div/a");
        WebElement cookieAcceptButtonElement = driver.findElement(cookieAcceptButtonLocator);

        if (cookieBlockElement.isDisplayed()){
            cookieAcceptButtonElement.click();
        }

        inputFirstNameElement.isDisplayed();
        inputFirstNameElement.sendKeys(firstName);

    }
}