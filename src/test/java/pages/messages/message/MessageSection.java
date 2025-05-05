package pages.messages.message;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pages.messages.MessagesPage;
import utils.LoadableComponent;

/**
 * Обертка блока с сообщениями на странице сообщений {@link MessagesPage}
 */
@Slf4j
public class MessageSection extends LoadableComponent {

    private static final By SOME_MESSAGE = By.xpath(".//msg-message");
    private static final By MESSAGE_INPUT_FIELD = By.xpath(".//*[@data-tsid='write_msg_input-input']");
    private static final By SEND_BUTTON = By.xpath(".//*[@data-tsid='button_send']");
    private final SelenideElement item;

    /**
     * Конструктор для вызова метода с валидацией прогрузки страницы
     *
     * @param item корневой элемент
     */
    public MessageSection(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        log.info("Валидация блока с сообщениями");

        item.shouldBe(visible.because("Список сообщений не прогрузился"));

        item
            .$(MESSAGE_INPUT_FIELD)
            .shouldBe(visible.because("Поле ввода сообщения не найдено"));

        item
            .$(SEND_BUTTON)
            .shouldBe(visible.because("Кнопка отправки сообщения не найдена"));

        log.info("Блок с сообщениями успешно провалидирован");
    }

    /**
     * Получить список сообщений в чате
     *
     * @return список оберток сообщений
     */

    public List<MessageWrapper> getAllMessagesInChat() {
        return item
            .$$(SOME_MESSAGE)
            .stream()
            .map(MessageWrapper::new)
            .toList();
    }

    /**
     * Отправить сообщение в чат
     *
     * @param message текст сообщения
     */
    public void sendMessage(String message) {
        item
            .$(MESSAGE_INPUT_FIELD)
            .setValue(message);

        item
            .$(SEND_BUTTON)
            .click();
    }
}
