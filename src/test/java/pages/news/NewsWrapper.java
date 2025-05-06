package pages.news;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.LoadableComponent;

/**
 * Обертка отдельной новости в секции новостей {@link NewsSection}
 */
public class NewsWrapper extends LoadableComponent {

    private static final By NEWS_WRAPPER = By.xpath(".//*[contains(@data-l, 'topicId')]");
    private final SelenideElement item;

    public NewsWrapper(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item.shouldBe(visible.because("Корневой элемент не прогрузился"));

        item
            .$(NEWS_WRAPPER)
            .shouldBe(visible.because("Основная секция новости не прогрузилась"));
    }
}
