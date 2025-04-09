package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

/**
 * Страница профиля пользователя (<a href="https://ok.ru/profile/">перейти</a>)
 * Позволяет получить основную информацию о пользователе, перейти в другие разделы
 * */
public class ProfilePage {

    private final static String PROFILE_URL = "https://ok.ru/profile/";

    private final SelenideElement userName = $(By.xpath("//*[@id=\"hook_Block_Navigation\"]/div/div/div[1]/a/div"));
    private final SelenideElement guestButton = $(By.xpath("//*[@id=\"hook_Block_HeaderTopNewEventsInToolbar\"]/a"));
    private final SelenideElement messagesButton = $(By.xpath("//*[@id=\"msg_toolbar_button\"]"));

    /**
     * Вернуть имя текущего пользователя
     * */
    public String getUserName() {
        return userName.getText();
    }

    /**
     * Перейти на страницу конкретного пользователя по его ID
     *
     * @param profileId ID профиля для перехода
     * */
    public ProfilePage goToProfilePage(String profileId) {
        open(PROFILE_URL + profileId);
        return this;
    }

    /**
     * Выйти из аккаунта
     * TODO: обычный выход через UI не работает, пока оставлено через closeWebDriver()
     * */
    public LoginPage signOut() {
        closeWebDriver();
        return new LoginPage().open();
    }

    /**
     * Перейти на страницу со списком гостей
     * */
    public GuestPage goToGuestPage() {
        guestButton.shouldBe(visible.because("Кнопка списка гостей отсутствует")).click();
        return new GuestPage();
    }

    /**
     * Перейти на страницу сообщений
     * */
    public MessagesPage goToMessagesPage() {
        messagesButton.shouldBe(visible.because("Кнопка сообщений отсутствует")).click();
        return new MessagesPage();
    }

}
