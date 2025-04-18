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

    public ChatWrapper(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item.shouldBe(visible.because("Чат отсутствует"));
    }

    public void clickOnChat() {
        item.click();
    }
}
