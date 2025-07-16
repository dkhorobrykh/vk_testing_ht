package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

/**
 * Страница авторизации (<a href="https:/ok.ru">перейти</a>) Используется для ввода логина и пароля и перехода на
 * основную страницу профиля
 */
public class LoginPage extends BasePage {

    private static final By LOGIN_INPUT = By.xpath(".//*[@id='field_email']");
    private static final By PASSWORD_INPUT = By.xpath(".//*[@id='field_password']");
    private static final By LOGIN_BUTTON = By.xpath(".//*[contains(@data-l, 'sign_in')]");
    private static final By WRONG_LOGIN_EXCEPTION = By.xpath(".//*[contains(@class, 'login_error')]");

    public static final String WRONG_LOGIN_EXCEPTION_TEXT = "Неправильно указан логин и/или пароль";

    /**
     * Ввести логин пользователя в соответствующее поле
     *
     * @param login логин пользователя
     *
     * @return текущая страница с логином
     */
    public LoginPage enterLogin(String login) {
        $(LOGIN_INPUT)
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
        $(PASSWORD_INPUT)
            .shouldBe(visible.because("Поле ввода пароля отсутствует"))
            .setValue(password);
        return this;
    }

    /**
     * Нажать кнопку входа и перейти на страницу профиля
     *
     * @return страница профиля
     */
    // Тут была попытка в параметрический полиморфизм)
    public BasePage login(boolean needRedirect) {
        $(LOGIN_BUTTON)
            .shouldBe(visible.because("Кнопка входа отсутствует"))
            .click();
        return needRedirect ? new ProfilePage() : this;
    }

    /**
     * Нажать кнопку входа. В случае не передачи флага needRedirect, ожидаем корректный переход на ProfilePage
     *
     * @return текущая страница с логином
     */
    public ProfilePage login() {
        return (ProfilePage) login(true);
    }

    /**
     * Получить текст ошибки при неправильных данных для входа
     *
     * @return текст ошибки
     */
    public String getWrongLoginExceptionText() {
        return $(WRONG_LOGIN_EXCEPTION)
            .shouldBe(visible.because("Ошибка неправильных данных для входа отсутствует"))
            .getText();
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item
            .$(LOGIN_INPUT)
            .shouldBe(visible.because("Поле ввода логина отсутствует"));

        item
            .$(PASSWORD_INPUT)
            .shouldBe(visible.because("Поле ввода пароля отсутствует"));

        item
            .$(LOGIN_BUTTON)
            .shouldBe(visible.because("Кнопка входа отсутствует"));
    }
}
