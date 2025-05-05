package pages.guests;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.BasePage;

/**
 * Страница гостей (<a href="https://ok.ru/guests">перейти</a>) Выводит список гостей, посетивших страницу текущего
 * пользователя.
 */
public class GuestPage extends BasePage {

    public static final String GUEST_LIST_URL = "https://ok.ru/guests";

    private static final By SOME_GUEST = By.xpath(".//*[contains(@data-l, 'targetUserId')]");

    /**
     * Получить имя последнего гостя
     *
     * @return имя последнего гостя
     */
    public String getFirstGuestName() {
        return new GuestWrapper($$(SOME_GUEST).get(0)).getName();
    }

    @Override
    public void validateComponent(SelenideElement item) {
        $$(SOME_GUEST).shouldHave(CollectionCondition
            .sizeGreaterThan(0)
            .because("Нет ни одного гостя в списке"));
    }
}
