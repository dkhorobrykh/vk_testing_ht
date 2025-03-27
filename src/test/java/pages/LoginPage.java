package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class LoginPage {

    private static final String LOGIN_URL = "https://ok.ru/";

    private SelenideElement loginInput = $(By.xpath("//*[@id=\"field_email\"]"));
    private SelenideElement passwordInput = $(By.xpath("//*[@id=\"field_password\"]"));
    private SelenideElement loginButton = $(By.xpath("//input[@value='Войти в Одноклассники']"));
    private SelenideElement wrongLoginException = $(By.xpath("//div[contains(@class, 'login_error')]"));

    public static final String WRONG_LOGIN_EXCEPTION_TEXT = "Неправильно указан логин и/или пароль";

    public LoginPage open() {
        Selenide.open(LOGIN_URL);
        loginButton.shouldBe(visible);
        return this;
    }

    public LoginPage enterLogin(String login) {
        loginInput.setValue(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public ProfilePage login() {
        loginButton.click();
        return page(ProfilePage.class);
    }

    public String getWrongLoginExceptionText() {
        wrongLoginException.shouldBe(visible);
        return wrongLoginException.getText();
    }

}
