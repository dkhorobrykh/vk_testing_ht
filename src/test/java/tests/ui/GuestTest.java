package tests.ui;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import tests.TestType;

@Slf4j
public class GuestTest extends BaseUITest {

    @Test
    @Tag(TestType.SLOW)
    @DisplayName("Перейти на страницу другого пользователя, проверить отображение в списке гостей")
    public void guestShouldBeDisplayedInTheGuestList() {
        log.info("Авторизуемся с пользователем #1");
        var profilePage1 = new LoginPage()
            .enterLogin(firstUser.getLogin())
            .enterPassword(firstUser.getPassword())
            .login();

        log.info("Переходим на профиль пользователя #2");
        var profilePage2 = profilePage1
            .goToProfilePage(secondUser.getId());

        log.info("Выходим из профиля пользователя #1");
        var loginPage = profilePage1.signOut();

        log.info("Авторизуемся с пользователем #2");
        profilePage2 = loginPage
            .enterLogin(secondUser.getLogin())
            .enterPassword(secondUser.getPassword())
            .login();

        log.info("Переходим на страницу гостей пользователя #2");
        var guestPage = profilePage2
            .goToGuestPage();

        log.info("Проверяем, появился ли пользователь #1 в списке гостей пользователя #2");
        var firstGuestName = guestPage.getFirstGuestName();
        assertThat(firstGuestName)
            .as("Имя первого гостя должно совпадать с именем пользователя #1")
            .isEqualTo(firstUser.getName());
    }

}
