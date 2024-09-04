package pages.google;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import pages.BasePage;

public class SelenideBlogPage extends BasePage {

    private static final By HEADER = By.cssSelector(".short h3");

    public SelenideBlogPage waitUntilHeaderIsVisible(){
        Selenide.$(HEADER).shouldBe(Condition.visible);
        return this;
    }

    public SelenideBlogPage checkHeaderText(String text){
        Selenide.$(HEADER).shouldHave(Condition.text(text));
        return this;
    }
}
