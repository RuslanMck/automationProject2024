package pages.google;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import pages.BasePage;

public class SelenideHomePage extends BasePage {

    private static final By DONATE_HEADER = By.cssSelector(".donate_header h1");
    private static final By BLOG_LINK = By.xpath("//a[text()='Blog']");

    public SelenideHomePage waitUntilHeaderIsVisible(){
        Selenide.$(DONATE_HEADER).shouldBe(Condition.visible);
        return this;
    }

    public SelenideHomePage verifyHeaderText(String text){
        Selenide.$(DONATE_HEADER).shouldHave(Condition.text(text));
        return this;
    }

    public SelenideHomePage clickBlogLink(){
        Selenide.$(BLOG_LINK).click();
        return this;
    }
}
