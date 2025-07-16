package pages.messages.chat;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.LoadableComponent;

public class ChatInfoSection extends LoadableComponent {

    private static final By ACTIONS_LIST = By.xpath(".//msg-chat-info-actions");
    private static final By DELETE_BUTTON = By.xpath(".//*[@data-tsid='remove-dialog-btn']");
    private static final By FINAL_DELETE_BUTTON = By.xpath(".//*[@data-tsid='confirm-primary']");
    private final SelenideElement item;

    public ChatInfoSection(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item.shouldBe(visible.because("Корневой элемент не прогрузился"));

        item
            .$(ACTIONS_LIST)
            .shouldBe(visible.because("Список действий над чатом не прогрузился"));
    }

    public void deleteChat() {
        item
            .$(DELETE_BUTTON)
            .shouldBe(visible.because("Кнопка удаления чата отсутствует"))
            .click();

        $(FINAL_DELETE_BUTTON)
            .shouldBe(visible.because("Кнопка подтверждения удаления чата отсутствует"))
            .click();
    }
}
