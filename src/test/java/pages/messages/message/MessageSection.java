package pages.messages.message;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pages.messages.MessagesPage;
import utils.LoadableComponent;

/**
 * Обертка блока с сообщениями на странице сообщений {@link MessagesPage}
 */
@Slf4j
public class MessageSection extends LoadableComponent {

    private final SelenideElement item;

    private static final By someMessage = By.xpath(".//msg-message");
    private static final By messageInputField = By.xpath(".//*[@data-tsid='write_msg_input-input']");
    private static final By sendButton = By.xpath(".//*[@data-tsid='button_send']");

    public MessageSection(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        log.info("Валидация блока с сообщениями");

        item.shouldBe(visible.because("Список сообщений не прогрузился"));

        item
            .$(messageInputField)
            .shouldBe(visible.because("Поле ввода сообщения не найдено"));

        item
            .$(sendButton)
            .shouldBe(visible.because("Кнопка отправки сообщения не найдена"));

        log.info("Блок с сообщениями успешно провалидирован");
    }

    /**
     * Получить последнее сообщение в чате
     *
     * @return обертка последнего сообщения
     */

    public MessageWrapper getLastMessage() {
        return new MessageWrapper(item
            .$$(someMessage)
            .last()
            .shouldBe(visible.because("Последнее сообщение отсутствует")));
    }

    /**
     * Отправить сообщение в чат
     *
     * @param message текст сообщения
     */
    public void sendMessage(String message) {
        item
            .$(messageInputField)
            .setValue(message);

        item
            .$(sendButton)
            .click();
    }
}
