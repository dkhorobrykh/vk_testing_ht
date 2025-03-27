package tests.ui;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class LoginTest extends BaseUITest {

    private static final LoginPage loginPage = new LoginPage();

    private static final String secondUserLogin = System.getenv("SECOND_USER_LOGIN");
    private static final String secondUserPassword = System.getenv("SECOND_USER_PASSWORD");
    private static final String secondUserName = System.getenv("SECOND_USER_NAME");

    private static final String wrongUserLogin = "wrong";
    private static final String wrongUserPassword = "wrong";

    @Test
    public void login_withValidCredentials_shouldRedirectToProfilePage() {
        var profilePage = loginPage
            .open()
            .enterLogin(secondUserLogin)
            .enterPassword(secondUserPassword)
            .login();

        assertEquals(secondUserName, profilePage.getUserName());
    }

    @Test
    public void login_withInvalidCredentials_shouldShowException() {
        loginPage
            .open()
            .enterLogin(wrongUserLogin)
            .enterPassword(wrongUserPassword)
            .login();

        assertEquals(LoginPage.WRONG_LOGIN_EXCEPTION_TEXT, loginPage.getWrongLoginExceptionText());
    }
}
