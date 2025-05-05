package pages.messages.chat;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.LoadableComponent;

/**
 * Обертка секции создания нового чата
 */
public class NewChatSection extends LoadableComponent {

    private static final By FINISH_CHAT_CREATE_BUTTON = By.xpath(".//*[@data-tsid='finish_create_chat_button']");
    private final SelenideElement item;

    /**
     * Конструктор для вызова метода с валидацией прогрузки страницы
     *
     * @param item корневой элемент
     */
    public NewChatSection(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item.shouldBe(visible.because("Секция создания нового чата не прогрузилась"));

        item
            .$(FINISH_CHAT_CREATE_BUTTON)
            .shouldBe(visible.because("Кнопка завершения создания нового чата не найдена"));
    }

    /**
     * Кликнуть по кнопке завершения создания нового чата
     */
    public void clickOnCreateNewChatButton() {
        item
            .$(FINISH_CHAT_CREATE_BUTTON)
            .click();
    }
}
