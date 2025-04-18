package pages.messages.chat;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pages.messages.MessagesPage;
import utils.LoadableComponent;

/**
 * Обертка блока с чатами на странице сообщений {@link MessagesPage}
 */
@Slf4j
public class ChatSection extends LoadableComponent {

    private final SelenideElement item;

    private static final By createChatMenuButton = By.xpath(".//*[contains(@data-l, 'createMenu')]");
    private static final By createChatButton = By.xpath(".//*[@data-tsid='plus_create_chat']");
    private static final By someChat = By.xpath(".//msg-chats-list-item");
    private static final By newChatSection = By.xpath(".//msg-new-chat");

    /**
     * Конструктор, вызывающий валидацию страницы
     *
     * @param item корневой элемент
     */
    public ChatSection(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        log.info("Валидация блока с чатами");

        item.shouldBe(visible.because("Блок с чатами не отображается"));

        item
            .$(createChatMenuButton)
            .hover()
            .shouldBe(visible.because("Кнопка открытия меню создания чата отсутствует"));

        item
            .$$(someChat)
            .shouldHave(CollectionCondition
                .sizeGreaterThan(0)
                .because("Нет ни одного чата в списке"));

        log.info("Блок с чатами успешно провалидирован");
    }

    /**
     * Создать новый пустой чат
     */
    public void createNewEmptyChat() {
        log.info("Создаем новый пустой чат");

        item.shouldBe(visible.because("Список чатов не прогрузился"));

        log.info("Открываем меню создания нового чата");
        item
            .$(createChatMenuButton)
            .hover()
            .click();

        log.info("Создаем новый чат");
        item
            .$(createChatButton)
            .shouldBe(visible.because("Кнопка создания нового чата отсутствует"))
            .click();

        log.info("Завершаем создание нового чата");

        var newChatSectionElement =
            new NewChatSection($(newChatSection).shouldBe(visible.because("Секция создания нового чата отсутствует")));
        newChatSectionElement.clickOnCreateNewChatButton();

        log.info("Чат успешно создан");
    }

    /**
     * Получить список всех чатов на странице
     *
     * @return список чатов в обертке {@link ChatWrapper}
     */
    public List<ChatWrapper> getAllChats() {
        return item
            .$$(someChat)
            .stream()
            .map(ChatWrapper::new)
            .toList();
    }

    public ChatWrapper getChat(Integer index) {
        log.info(
            "Получаем чат с индексом {}",
            index
        );

        var chatElement = item
            .$$(By.xpath(".//msg-chats-list-item"))
            .get(index);
        return new ChatWrapper(chatElement);
    }
}
