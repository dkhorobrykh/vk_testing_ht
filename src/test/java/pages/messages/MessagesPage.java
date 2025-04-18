package pages.messages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.messages.chat.ChatSection;
import pages.messages.message.MessageSection;
import pages.messages.message.MessageWrapper;
import utils.LoadableComponent;

/**
 * Страница сообщений (<a href="https://ok.ru/messages">перейти</a>) Позволяет получать и отправлять сообщения текущего
 * пользователя
 */
public class MessagesPage extends BasePage {

    public static final String MESSAGES_URL = "https://ok.ru/messages";

    private final By chatsSection = By.xpath(".//msg-chats-panel");
    private final By messageSection = By.xpath(".//msg-chat");

    /**
     * Открыть сообщения в чате
     *
     * @param index индекс чата в списке
     */
    public void openChat(Integer index) {
        getChatSection()
            .getChat(index)
            .clickOnChat();
    }

    /**
     * Получить секцию с чатами
     *
     * @return секция с чатами
     */
    private ChatSection getChatSection() {
        return new ChatSection($(chatsSection).shouldBe(visible.because("Секция с чатами отсутствует")));
    }

    /**
     * Отправить сообщение в открытый чат
     *
     * @param message текст сообщения для отправки
     */
    public void sendMessage(String message) {
        getMessageSection().sendMessage(message);
    }

    /**
     * Получить последнее сообщение в открытом чате
     *
     * @return последнее сообщение в открытом чате
     */
    public MessageWrapper getLastMessage() {
        return getMessageSection().getLastMessage();
    }

    /**
     * Создать новый пустой чат
     */
    public void createEmptyChat() {
        var chatSection = getChatSection();

        chatSection.createNewEmptyChat();
    }

    /**
     * Получить секцию с сообщениями
     *
     * @return секция с сообщениями
     */
    private MessageSection getMessageSection() {
        return new MessageSection($(messageSection).shouldBe(visible.because("Секция с сообщениями отсутствует")));
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item
            .$(chatsSection)
            .shouldBe(visible.because("Секция с чатами отсутствует"));
    }
}
