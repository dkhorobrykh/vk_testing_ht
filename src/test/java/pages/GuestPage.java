package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

/**
 * Страница гостей (<a href="https://ok.ru/guests">перейти</a>) Выводит список гостей, посетивших страницу текущего
 * пользователя.
 */
public class GuestPage {

    public static final String GUEST_LIST_URL = "https://ok.ru/guests";

    private final By firstGuestName = By.xpath("//*[@class='user-grid-card_cnt'][1]//a[@class='n-t bold']");

    /**
     * Получить имя последнего гостя
     *
     * @return имя последнего гостя
     */
    public String getFirstGuestName() {
        return $(firstGuestName)
            .shouldBe(visible.because("Имя последнего гостя отсутствует на странице"))
            .getText();
    }

}
