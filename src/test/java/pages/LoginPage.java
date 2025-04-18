package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

/**
 * Страница авторизации (<a href="https:/ok.ru">перейти</a>) Используется для ввода логина и пароля и перехода на
 * основную страницу профиля
 */
public class LoginPage extends BasePage {

    public static final String LOGIN_URL = "https://ok.ru/";

    private static final By loginInput = By.xpath(".//*[@id='field_email']");
    private static final By passwordInput = By.xpath(".//*[@id='field_password']");
    private static final By loginButton = By.xpath(".//*[contains(@data-l, 'sign_in')]");
    private static final By wrongLoginException = By.xpath("//*[contains(@class, 'login_error')]");

    public static final String WRONG_LOGIN_EXCEPTION_TEXT = "Неправильно указан логин и/или пароль";

    /**
     * Открыть страницу логина
     */
    public LoginPage open() {
        Selenide.open(LOGIN_URL);
        $(loginButton).shouldBe(visible.because("Кнопка входа отсутствует, страница не прогрузилась"));
        return this;
    }

    /**
     * Ввести логин пользователя в соответствующее поле
     *
     * @param login логин пользователя
     *
     * @return текущая страница с логином
     */
    public LoginPage enterLogin(String login) {
        $(loginInput)
            .shouldBe(visible.because("Поле ввода логина отсутствует"))
            .setValue(login);
        return this;
    }

    /**
     * Ввести пароль пользователя в соответствующее поле
     *
     * @param password пароль пользователя
     *
     * @return текущая страница с логином
     */
    public LoginPage enterPassword(String password) {
        $(passwordInput)
            .shouldBe(visible.because("Поле ввода пароля отсутствует"))
            .setValue(password);
        return this;
    }

    /**
     * Нажать кнопку входа и, в случае успешной авторизации, перейти на страницу профиля
     *
     * @return страница профиля
     */
    public ProfilePage login() {
        $(loginButton)
            .shouldBe(visible.because("Кнопка входа отсутствует"))
            .click();
        return new ProfilePage();
    }

    /**
     * Получить текст ошибки при неправильных данных для входа
     *
     * @return текст ошибки
     */
    public String getWrongLoginExceptionText() {
        return $(wrongLoginException)
            .shouldBe(visible.because("Ошибка неправильных данных для входа отсутствует"))
            .getText();
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item.shouldBe(visible.because("Страница логина не прогрузилась"));

        item
            .$(loginInput)
            .shouldBe(visible.because("Поле ввода логина отсутствует"));

        item
            .$(passwordInput)
            .shouldBe(visible.because("Поле ввода пароля отсутствует"));

        item
            .$(loginButton)
            .shouldBe(visible.because("Кнопка входа отсутствует"));
    }
}
