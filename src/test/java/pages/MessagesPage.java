package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

/**
 * Страница сообщений (<a href="https://ok.ru/messages">перейти</a>) Позволяет получать и отправлять сообщения текущего
 * пользователя
 */
public class MessagesPage {

    public static final String MESSAGES_URL = "https://ok.ru/messages";

    private final By secondChat = By.xpath("//msg-chats-list/msg-chats-list-item[2]/a");
    private final By lastMessage = By.xpath("//msg-message[last()]//*[contains(@data-tsid, 'message_text')]");
    private final By firstChat = By.xpath("//msg-chats-list/msg-chats-list-item[1]/a");

    private final By messageInputField = By.xpath(".//msg-input");
    private final By sendButton = By.xpath(".//*[@data-tsid='button_send']");

    /**
     * Открыть первый чат в списке и получить последнее сообщение из него
     */
    public String getLastMessage() {
        $(secondChat)
            .shouldBe(visible.because("Второй диалог в списке сообщений отсутствует"))
            .click();
        return $(lastMessage)
            .shouldBe(visible.because("Последнее сообщение в чате отсутствует"))
            .getText();
    }

    /**
     * Открыть чат и отправить в него сообщение
     *
     * @param message текст сообщения для отправки
     */
    public void writeMessageToEmptyChat(String message) {
        $(firstChat)
            .shouldBe(visible.because("Требуемый чат для отправки сообщения отсутствует"))
            .click();
        $(messageInputField)
            .shouldBe(visible.because("Поле для ввода текста сообщения отсутствует"))
            .sendKeys(message);
        $(sendButton)
            .shouldBe(visible.because("Кнопка отправки сообщения отсутствует"))
            .click();
    }

    /**
     * Открыть чат и получить последнее сообщение после отправки
     */
    public String getLastMessageInEmptyChat() {
        $(firstChat)
            .shouldBe(visible.because("Требуемый чат для проверки сообщения отсутствует"))
            .click();
        return $(lastMessage)
            .shouldBe(visible.because("Последнее сообщение в чате отсутствует"))
            .getText();
    }

}
