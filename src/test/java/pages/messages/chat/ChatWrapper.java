package pages.messages.chat;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import pages.messages.MessagesPage;
import utils.LoadableComponent;

/**
 * Обертка одного из чатов на странице сообщений {@link MessagesPage}
 */
public class ChatWrapper extends LoadableComponent {

    private final SelenideElement item;

    /**
     * Конструктор для вызова метода с валидацией прогрузки страницы
     *
     * @param item корневой элемент
     */
    public ChatWrapper(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item.shouldBe(visible.because("Чат отсутствует"));
    }

    /**
     * Кликнуть по чату
     */
    public void clickOnChat() {
        item.click();
    }
}
