package pages.messages.message;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

        item.$(messageInputField)
            .shouldBe(visible.because("Поле ввода сообщения не найдено"));

        item.$(sendButton)
            .shouldBe(visible.because("Кнопка отправки сообщения не найдена"));

        log.info("Блок с сообщениями успешно провалидирован");
    }


    public List<MessageWrapper> getAllMessages(Integer index) {
        return item
            .$$(someMessage)
            .stream()
            .map(MessageWrapper::new)
            .toList();
    }

    public MessageWrapper getMessage(Integer index) {
        return new MessageWrapper(item
            .$$(someMessage)
            .get(index)
            .shouldBe(visible.because("Сообщение с индексом %s отсутствует".formatted(index))));
    }

    public MessageWrapper getLastMessage() {
        return new MessageWrapper(item
            .$$(someMessage)
            .last()
            .shouldBe(visible.because("Последнее сообщение отсутствует")));
    }

    public void sendMessage(String message) {
        item
            .$(messageInputField)
            .setValue(message);

        item
            .$(sendButton)
            .click();
    }
}
