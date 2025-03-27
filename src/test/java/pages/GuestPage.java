package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class GuestPage {

    public static final String GUEST_LIST_URL = "https://ok.ru/guests";

    private SelenideElement firstGuestName = $(By.xpath("//*[@id=\"listBlockPanelUserGuests\"]/div[2]/div/div/div/div" +
        "/div[2]/div[1]/a"));

    public String getFirstGuestName() {
        firstGuestName.shouldBe(visible);
        return firstGuestName.getText();
    }

}
