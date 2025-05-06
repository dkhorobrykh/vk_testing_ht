package pages.guests;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class GuestPageFactory {

    public static final By EMPTY_FRIENDS_STUB = By.xpath(".//*[contains(@class, 'stub-empty  __friends')]");

    public static IGuestPage get(SelenideElement item) {
        if (item
            .$(EMPTY_FRIENDS_STUB)
            .isDisplayed()) {
            return new GuestStubPage();
        } else {
            return new GuestPage();
        }
    }
}
