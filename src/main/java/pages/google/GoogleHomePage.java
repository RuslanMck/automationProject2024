package pages.google;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class GoogleHomePage extends BasePage {

    private static final By ACCEPT_COOKIES_BUTTON = By.xpath("//button/div[contains(text(), 'Alles accepteren')]");
    private static final By SEARCH_INPUT_FIELD = By.xpath("//textarea");
    private static final By SEARCH_RESULTS = By.xpath("//h3[contains(text(), 'Selenide')]");

    @FindBy(xpath = "//textarea")
    SelenideElement searchInput;

    @FindBy(xpath = "//h3[contains(text(), 'Selenide')]")
    ElementsCollection searchResults;

    public GoogleHomePage waitUntilAcceptCookieButtonIsVisible(){
        Selenide.$(ACCEPT_COOKIES_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public GoogleHomePage clickCookieAcceptButton(){
        Selenide.$(ACCEPT_COOKIES_BUTTON).click();
        return this;
    }

    public GoogleHomePage waitUntilSearchInputFieldIsVisible(){
        Selenide.$(SEARCH_INPUT_FIELD).shouldBe(Condition.visible);
        return this;
    }

    public GoogleHomePage inputSearchKey(String searchKey){
        Selenide.$(SEARCH_INPUT_FIELD).setValue(searchKey);
        return this;
    }

    public GoogleHomePage waitUntilResultsAreVisible(){
        Selenide.$$(SEARCH_RESULTS).filter(Condition.visible);
        return this;
    }

    public GoogleHomePage verifyNumberOfResults(int expectedResults){
        Selenide.$$(SEARCH_RESULTS).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(expectedResults));
        return this;
    }

    public GoogleHomePage clickFirstResult(){
        Selenide.$$(SEARCH_RESULTS).get(0).click();
        return  this;
    }

    public GoogleHomePage pressEnter(){
        Selenide.$(SEARCH_INPUT_FIELD).pressEnter();
        return this;
    }
}
