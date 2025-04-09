package tests.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class LoginTest extends BaseUITest {

    private static final String wrongUserLogin = "wrong";
    private static final String wrongUserPassword = "wrong";

    @Test
    public void loginWithValidCredentialsShouldRedirectToProfilePage() {
        var loginPage = new LoginPage();

        var profilePage = loginPage
            .open()
            .enterLogin(SECOND_USER_LOGIN)
            .enterPassword(SECOND_USER_PASSWORD)
            .login();

        assertEquals(SECOND_USER_NAME, profilePage.getUserName(), "Неверное имя пользователя на главной странице");
    }

    @Test
    public void loginWithInvalidCredentialsShouldShowException() {
        var loginPage = new LoginPage();

        loginPage
            .open()
            .enterLogin(wrongUserLogin)
            .enterPassword(wrongUserPassword)
            .login();

        assertEquals(LoginPage.WRONG_LOGIN_EXCEPTION_TEXT, loginPage.getWrongLoginExceptionText(), "Неверный текст " +
            "ошибки при вводе некорректных данных для входа");
    }
}
