package pages.guests;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class GuestPageFactory {

    public static final By EMPTY_FRIENDS_STUB = By.xpath(".//*[contains(@class, 'stub-empty  __friends')]");

    public static IGuestPage get(SelenideElement item) {
        if (item
            .$(EMPTY_FRIENDS_STUB)
            .isDisplayed()) {
            log.info("Вернули заглушку гостей");
            return new GuestStubPage();
        } else {
            log.info("Вернули полноценную реализацию гостей");
            return new GuestPage();
        }
    }
}
