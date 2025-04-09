package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

/**
 * Страница гостей (<a href="https://ok.ru/guests">перейти</a>)
 * Выводит список гостей, посетивших страницу текущего пользователя.
 */
public class GuestPage {

    public static final String GUEST_LIST_URL = "https://ok.ru/guests";

    private final SelenideElement firstGuestName = $(By.xpath("//*[@id=\"listBlockPanelUserGuests\"]/div[2]/div/div" +
        "/div/div/div[2]/div[1]/a"));

    /**
     * Получает имя последнего гостя
     * */
    public String getFirstGuestName() {
        return firstGuestName.shouldBe(visible.because("Имя последнего гостя отсутствует на странице")).getText();
    }

}
