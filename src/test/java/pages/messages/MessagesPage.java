package pages.messages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.messages.chat.ChatSection;
import pages.messages.message.MessageSection;

/**
 * Страница сообщений (<a href="https://ok.ru/messages">перейти</a>) Позволяет получать и отправлять сообщения текущего
 * пользователя
 */
public class MessagesPage extends BasePage {

    public static final String MESSAGES_URL = "https://ok.ru/messages";

    private static final By CHATS_SECTION = By.xpath(".//msg-chats-panel");
    private static final By MESSAGE_SECTION = By.xpath(".//msg-chat");

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
    public ChatSection getChatSection() {
        return new ChatSection($(CHATS_SECTION).shouldBe(visible.because("Секция с чатами отсутствует")));
    }

    /**
     * Получить секцию с сообщениями
     *
     * @return секция с сообщениями
     */
    public MessageSection getMessageSection() {
        return new MessageSection($(MESSAGE_SECTION).shouldBe(visible.because("Секция с сообщениями отсутствует")));
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item
            .$(CHATS_SECTION)
            .shouldBe(visible.because("Секция с чатами отсутствует"));
    }
}
