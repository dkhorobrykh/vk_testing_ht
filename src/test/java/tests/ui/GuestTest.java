package tests.ui;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.GuestPage;
import pages.LoginPage;
import pages.ProfilePage;

public class GuestTest extends BaseUITest {

    private LoginPage loginPage = new LoginPage();
    private GuestPage guestPage = new GuestPage();
    private ProfilePage profilePage = new ProfilePage();

    private final static String firstUserLogin = System.getenv("FIRST_USER_LOGIN");
    private final static String firstUserPassword = System.getenv("FIRST_USER_PASSWORD");
    private final static String secondUserId = System.getenv("SECOND_USER_ID");
    private final static String secondUserLogin = System.getenv("SECOND_USER_LOGIN");
    private final static String secondUserPassword = System.getenv("SECOND_USER_PASSWORD");

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    public void guest_shouldBeDisplayedInTheGuestList() {
        // authorize with the first user
        loginPage
            .open()
            .enterLogin(firstUserLogin)
            .enterPassword(firstUserPassword)
            .login();

        // go to the second user's profile
        profilePage = profilePage
            .goToProfilePage(secondUserId);

        // sign out from the first user
        loginPage = profilePage.signOut();

        // authorize with the second user
        profilePage = loginPage
            .enterLogin(secondUserLogin)
            .enterPassword(secondUserPassword)
            .login();

        // go to the guest page
        guestPage = profilePage
            .goToGuestPage();

        // check that the first user is in the guest list
        assertEquals("Иванов Иван", guestPage.getFirstGuestName());
    }

}
