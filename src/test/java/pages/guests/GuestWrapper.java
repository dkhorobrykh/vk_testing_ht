package pages.guests;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.LoadableComponent;

/**
 * Обертка отдельного гостя на странице гостей {@link GuestPage}
 */
public class GuestWrapper extends LoadableComponent {

    private final SelenideElement item;

    private static final By GUEST_NAME = By.xpath(".//*[@class='n-t bold']");

    /**
     * Конструктор для вызова метода с валидацией прогрузки страницы
     *
     * @param item корневой элемент
     */
    public GuestWrapper(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item.shouldBe(visible.because("Гость не прогрузился"));

        item
            .$(GUEST_NAME)
            .shouldBe(visible.because("Имя гостя отсутствует на странице"));
    }

    /**
     * Получить имя гостя
     *
     * @return имя гостя
     */
    public String getName() {
        return item
            .$(GUEST_NAME)
            .getText();
    }
}
