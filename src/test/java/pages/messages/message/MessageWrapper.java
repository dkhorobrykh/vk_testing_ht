package pages.messages.message;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import utils.LoadableComponent;

/**
 * Обертка отдельного сообщения в чате
 */
@Slf4j
public class MessageWrapper extends LoadableComponent {

    private final SelenideElement item;

    public MessageWrapper(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item.shouldBe(visible.because("Сообщение не прогрузилось"));
    }

    /**
     * Получить текст сообщения
     *
     * @return текст сообщения
     */
    public String getText() {
        return item.getText();
    }

}
