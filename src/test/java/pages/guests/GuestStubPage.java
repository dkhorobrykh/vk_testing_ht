package pages.guests;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import java.util.List;
import org.openqa.selenium.By;
import pages.BasePage;

public class GuestStubPage extends BasePage implements IGuestPage {

    private static final By STUB_IMAGE = By.xpath(".//*[contains(@class, 'stub-empty_img')]");

    @Override
    public List<GuestWrapper> getAllGuests() {
        return List.of();
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item.shouldBe(visible.because("Корневой элемент страницы гостей не прогрузился"));

        item
            .$(STUB_IMAGE)
            .shouldBe(visible.because("Картинка заглушки для списка гостей не прогрузилась"));
    }
}
