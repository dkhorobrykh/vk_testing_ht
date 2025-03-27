package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class ProfilePage {

    private final static String PROFILE_URL = "https://ok.ru/profile/";

    private SelenideElement userName = $(By.xpath("//*[@id=\"hook_Block_Navigation\"]/div/div/div[1]/a/div"));
    private SelenideElement profileButton = $(By.xpath("//*[@id=\"hook_Block_ToolbarUserDropdown\"]/div/button"));
    private SelenideElement signOutButton = $(By.xpath("//*[@id=\"user-dropdown-menu\"]/div[1]/div/div[1]/div[2]"));
    private SelenideElement finalSignOutButton = $(By.xpath("//input[@value='Выйти']"));
    private SelenideElement guestButton = $(By.xpath("//*[@id=\"hook_Block_HeaderTopNewEventsInToolbar\"]/a"));
    private SelenideElement messagesButton = $(By.xpath("//*[@id=\"msg_toolbar_button\"]"));

    public String getUserName() {
        return userName.getText();
    }

    public ProfilePage goToProfilePage(String profileId) {
        open(PROFILE_URL + profileId);
        return this;
    }

    public LoginPage signOut() {
//        profileButton.click();
//        signOutButton.shouldBe(visible);
//        signOutButton.click();
//        finalSignOutButton.shouldBe(visible);
//        finalSignOutButton.click();

        // почему-то не срабатывает последний клик по кнопке, придется оставить небольшой костыль
        closeWebDriver();
        new LoginPage().open();
        return page(LoginPage.class);
    }

    public GuestPage goToGuestPage() {
        guestButton.shouldBe(visible);
        guestButton.click();
        return page(GuestPage.class);
    }

    public MessagesPage goToMessagesPage() {
        messagesButton.shouldBe(visible);
        messagesButton.click();
        return page(MessagesPage.class);
    }

}
