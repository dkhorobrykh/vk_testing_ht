package pages.messages.chat;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.LoadableComponent;

/**
 * Обертка секции создания нового чата
 */
public class NewChatSection extends LoadableComponent {

    private final SelenideElement item;

    private static final By finishChatCreateButton = By.xpath(".//*[@data-tsid='finish_create_chat_button']");

    public NewChatSection(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item.shouldBe(visible.because("Секция создания нового чата не прогрузилась"));

        item
            .$(finishChatCreateButton)
            .shouldBe(visible.because("Кнопка завершения создания нового чата не найдена"));
    }

    public void clickOnCreateNewChatButton() {
        item
            .$(finishChatCreateButton)
            .click();
    }
}
