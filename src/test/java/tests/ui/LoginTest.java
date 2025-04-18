package tests.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        var profilePage = new LoginPage()
            .open()
            .enterLogin(SECOND_USER_LOGIN)
            .enterPassword(SECOND_USER_PASSWORD)
            .login();

        assertAll(
            () -> assertCurrentUrlEquals(ProfilePage.PROFILE_URL),
            () -> assertThat(profilePage.getUserName())
                .as("Имя пользователя должно совпадать с именем заходящего пользователя")
                .isEqualTo(SECOND_USER_NAME)
        );

    }

    @Test
    @Tag(TestType.FAST)
    @DisplayName("Залогиниться с неверными данными, должен вывести сообщение об ошибке")
    public void loginWithInvalidCredentialsShouldShowException() {
        var loginPage = new LoginPage();

        loginPage
            .open()
            .enterLogin(wrongUserLogin)
            .enterPassword(wrongUserPassword)
            .login();

        var wrongLoginExceptionText = loginPage.getWrongLoginExceptionText();
        assertThat(wrongLoginExceptionText)
            .as("Текст ошибки при вводе некорректных данных для входа должен совпадать с ожидаемым")
            .isEqualTo(LoginPage.WRONG_LOGIN_EXCEPTION_TEXT);
    }
}
