package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;

/**
 * Страница профиля пользователя (<a href="https://ok.ru/profile/">перейти</a>) Позволяет получить основную информацию о
 * пользователе, перейти в другие разделы
 */
public class ProfilePage {

    public final static String PROFILE_URL = "https://ok.ru/";

    private final By userName = By.xpath(".//*[contains(@data-l, 'userPage')]");
    private final By guestButton = By.xpath(".//*[contains(@data-l, 'guests')]");
    private final By messagesButton = By.xpath(".//*[@id='msg_toolbar_button']");
    private final By rightMenuButton = By.xpath(".//button[contains(@class, 'ucard-mini')]");
    private final By exitButton = By.xpath("//*[contains(@data-l, 'logout')]");

    /**
     * Вернуть имя текущего пользователя
     */
    public String getUserName() {
        return $(userName).getText();
    }

    /**
     * Перейти на страницу конкретного пользователя по его ID
     *
     * @param profileId ID профиля для перехода
     */
    public ProfilePage goToProfilePage(String profileId) {
        open(PROFILE_URL + "profile/" + profileId);
        return this;
    }

    /**
     * Выйти из аккаунта
     * TODO: обычный выход через UI не работает, пока оставлено через closeWebDriver()
     */
    public LoginPage signOut() {
        //        rightMenuButton.shouldBe(visible.because("Правое верхнее меню отсутствует")).click();
        //        exitButton.shouldBe(visible.because("Кнопка выхода отсутствует")).click();
        closeWebDriver();
        return new LoginPage().open();
    }

    /**
     * Перейти на страницу со списком гостей
     */
    public GuestPage goToGuestPage() {
        $(guestButton)
            .shouldBe(visible.because("Кнопка списка гостей отсутствует"))
            .click();
        return new GuestPage();
    }

    /**
     * Перейти на страницу сообщений
     */
    public MessagesPage goToMessagesPage() {
        $(messagesButton)
            .shouldBe(visible.because("Кнопка сообщений отсутствует"))
            .click();
        return new MessagesPage();
    }

}
