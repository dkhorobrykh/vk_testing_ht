package tests.ui;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProfilePage;
import tests.TestType;

public class LoginTest extends BaseUITest {

    private static final String wrongUserLogin = "wrong";
    private static final String wrongUserPassword = "wrong";

    @Test
    @Tag(TestType.FAST)
    @DisplayName("Залогиниться с корректными данными, должен перенаправить на основную страницу пользователя")
    public void loginWithValidCredentialsShouldRedirectToProfilePage() {
        var loginPage = new LoginPage();

        var profilePage = loginPage.open().enterLogin(SECOND_USER_LOGIN).enterPassword(SECOND_USER_PASSWORD).login();

        assertAll(
            () -> assertCurrentUrlEquals(ProfilePage.PROFILE_URL),
            () -> assertEquals(
                SECOND_USER_NAME,
                profilePage.getUserName(),
                "Неверное имя пользователя на главной " + "странице"
            )
        );

    }

    @Test
    @Tag(TestType.FAST)
    @DisplayName("Залогиниться с неверными данными, должен вывести сообщение об ошибке")
    public void loginWithInvalidCredentialsShouldShowException() {
        var loginPage = new LoginPage();

        loginPage.open().enterLogin(wrongUserLogin).enterPassword(wrongUserPassword).login();

        assertEquals(
            LoginPage.WRONG_LOGIN_EXCEPTION_TEXT,
            loginPage.getWrongLoginExceptionText(),
            "Неверный текст " + "ошибки при вводе некорректных данных для входа"
        );
    }
}
