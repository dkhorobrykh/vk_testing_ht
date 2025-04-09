package tests.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

@Slf4j
public class GuestTest extends BaseUITest {

    @Test
    public void guestShouldBeDisplayedInTheGuestList() {
        var loginPage = new LoginPage();

        log.info("Авторизируемся с пользователем #1");
        var profilePage1 = loginPage
            .open()
            .enterLogin(FIRST_USER_LOGIN)
            .enterPassword(FIRST_USER_PASSWORD)
            .login();

        log.info("Переходим на профиль пользователя #2");
        var profilePage2 = profilePage1
            .goToProfilePage(SECOND_USER_ID);

        log.info("Выходим из профиля пользователя #1");
        loginPage = profilePage1.signOut();

        log.info("Авторизируемся с пользователем #2");
        profilePage2 = loginPage
            .enterLogin(SECOND_USER_LOGIN)
            .enterPassword(SECOND_USER_PASSWORD)
            .login();

        log.info("Переходим на страницу гостей пользователя #2");
        var guestPage = profilePage2
            .goToGuestPage();

        log.info("Проверяем, появился ли пользователь #1 в списке гостей пользователя #2");
        assertEquals(FIRST_USER_NAME, guestPage.getFirstGuestName(), "Неверное имя пользователя на странице гостей");
    }

}
