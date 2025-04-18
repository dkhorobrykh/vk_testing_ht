package pages;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.guests.GuestPage;
import pages.messages.MessagesPage;

/**
 * Страница профиля пользователя (<a href="https://ok.ru/profile/">перейти</a>) Позволяет получить основную информацию о
 * пользователе, перейти в другие разделы
 */
public class ProfilePage extends BasePage {

    public static final String PROFILE_URL = "https://ok.ru/";

    private static final By userName = By.xpath(".//*[contains(@data-l, 'userPage')]");
    private static final By guestButton = By.xpath(".//*[contains(@data-l, 'guests')]");
    private static final By messagesButton = By.xpath(".//*[@id='msg_toolbar_button']");
    private static final By rightMenuButton = By.xpath(".//button[contains(@class, 'ucard-mini')]");
    private static final By exitButton = By.xpath(".//*[contains(@data-l, 'logout')]");
    private static final By finalExitButton = By.xpath(".//input[contains(@data-l, 'logout')]");

    /**
     * Вернуть имя текущего пользователя
     *
     * @return имя текущего пользователя
     */
    public String getUserName() {
        return $(userName).getText();
    }

    /**
     * Перейти на страницу конкретного пользователя по его ID
     *
     * @param profileId ID профиля для перехода
     * @return текущая страница профиля
     */
    public ProfilePage goToProfilePage(String profileId) {
        open(PROFILE_URL + "profile/" + profileId);
        return this;
    }

    /**
     * Выйти из аккаунта
     *
     * @return страница логина
     */
    public LoginPage signOut() {
        $(rightMenuButton)
            .shouldBe(visible.because("Правое верхнее меню отсутствует"))
            .hover()
            .click();
        $(exitButton)
            .shouldBe(visible.because("Кнопка выхода отсутствует"))
            .click();
        $(finalExitButton)
            .shouldBe(visible.because("Финальная кнопка выхода отсутствует"))
            .click();
        SelenideElement form = $(finalExitButton)
            .closest("form")
            .should(appear.because("Форма диалога выхода не найдена"));
        form.submit();
        return new LoginPage();
    }

    /**
     * Перейти на страницу со списком гостей
     *
     * @return страница со списком гостей
     */
    public GuestPage goToGuestPage() {
        $(guestButton)
            .shouldBe(visible.because("Кнопка списка гостей отсутствует"))
            .click();
        return new GuestPage();
    }

    /**
     * Перейти на страницу сообщений
     *
     * @return страница сообщений
     */
    public MessagesPage goToMessagesPage() {
        $(messagesButton)
            .shouldBe(visible.because("Кнопка сообщений отсутствует"))
            .click();
        return new MessagesPage();
    }

    @Override
    public void validateComponent(SelenideElement item) {

        item
            .$(userName)
            .shouldBe(visible.because("Имя пользователя не найдено"));

        item
            .$(guestButton)
            .shouldBe(visible.because("Кнопка списка гостей отсутствует"));

        item
            .$(messagesButton)
            .shouldBe(visible.because("Кнопка сообщений отсутствует"));

        item
            .$(rightMenuButton)
            .shouldBe(visible.because("Правое верхнее меню отсутствует"));
    }
}
