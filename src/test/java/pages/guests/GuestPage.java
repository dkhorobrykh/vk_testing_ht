package pages.guests;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import java.util.List;
import org.openqa.selenium.By;
import pages.BasePage;

/**
 * Страница гостей (<a href="https://ok.ru/guests">перейти</a>) Выводит список гостей, посетивших страницу текущего
 * пользователя.
 */
public class GuestPage extends BasePage implements IGuestPage {

    private static final By SOME_GUEST = By.xpath(".//*[contains(@data-l, 'targetUserId')]");

    @Override
    public List<GuestWrapper> getAllGuests() {
        var guests = $$(SOME_GUEST).shouldHave(CollectionCondition
            .sizeGreaterThan(0)
            .because("Нет ни одного гостя в списке"));

        return guests
            .stream()
            .map(GuestWrapper::new)
            .toList();
    }

    @Override
    public void validateComponent(SelenideElement item) {
        $$(SOME_GUEST).shouldHave(CollectionCondition
            .sizeGreaterThan(0)
            .because("Нет ни одного гостя в списке"));
    }
}
