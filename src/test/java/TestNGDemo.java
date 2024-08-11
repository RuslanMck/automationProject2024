import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class TestNGDemo {

    private WebDriver driver;

    private WebElement cookieBlock;
    private WebElement acceptButton;
    private WebElement firstNameInput;
    private WebElement lastNameInput;
    private WebElement emailInput;
    private WebElement messageInput;


    private String baseUrl = "https://soliterata.com/practice-form/";
    private By cookieLocator = By.xpath("//div[@data-elementor-id=\"4540\"]");
    private By cookieAcceptLocator = By.xpath("//*[text()='Accept']");
    private By firstNameInputLocator = By.xpath("//label[normalize-space(text())='First Name']" +
            "/following-sibling::input");
    private By lsatNameInputLocator = By.xpath("//label[normalize-space(text())='Last Name']" +
            "/following-sibling::input");
    private By emailInputLocator = By.xpath("//input[@id='form-field-email']");
    private By messageInputLocator = By.xpath("//textarea[@id='form-field-message']");
    private By genderSelectorLocator = By.xpath("//span[@class='elementor-field-option']");

    private String firstName = "Bob";
    private String message = "Test message 123";
    private String lastName = "Obama";
    private String email = "bob.obama@test.com";
    private List<WebElement> genderSelector;

    private void acceptCookies(){
        cookieBlock = driver.findElement(cookieLocator);
        acceptButton = driver.findElement(cookieAcceptLocator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        if(cookieBlock.isDisplayed()){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Accept']")));
            acceptButton.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()='Accept']")));
        }
    }

    @BeforeClass
    public void setUp(){
        System.setProperty("selenium.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get(baseUrl);

        acceptCookies();

        firstNameInput = driver.findElement(firstNameInputLocator);
        lastNameInput = driver.findElement(lsatNameInputLocator);
        emailInput = driver.findElement(emailInputLocator);
        messageInput = driver.findElement(messageInputLocator);
        genderSelector = driver.findElements(genderSelectorLocator);
    }

    @Test
    public void firstNameInputTests(){

        firstNameInput.sendKeys(firstName);

        Assert.assertEquals(firstNameInput.getAttribute("value"), firstName, "First name is not equals to expected " +
                "result");
    }

    @Test
    public void lastNameInputTest(){
        lastNameInput.sendKeys(lastName);

        Assert.assertTrue(ExpectedConditions.attributeToBe(lastNameInput, "value", lastName).apply(driver));
    }

    @Test
    public void emailInputTest(){
        emailInput.sendKeys(email);

        Assert.assertTrue(ExpectedConditions.attributeToBe(emailInput, "value", email).apply(driver));
    }

    @Test
    public void messageInputTest(){
        messageInput.sendKeys(message);

        Assert.assertEquals(messageInput.getAttribute("value"), message);

        messageInput.clear();

        /**
         * Check that input field is empty
         */
        Assert.assertTrue(ExpectedConditions.attributeToBe(messageInput, "valur", "").apply(driver));
    }

    @Test
    public void genderSelectorTest(){

        for(WebElement e : genderSelector){
            System.out.println(e.isSelected());
            Assert.assertFalse(e.isSelected());
        }
    }

    @AfterClass
    public void testDown(){
        driver.close();
    }
}
