package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

/**
 * Страница сообщений (<a href="https://ok.ru/messages">перейти</a>) Позволяет получать и отправлять сообщения текущего
 * пользователя
 */
public class MessagesPage {

    public static final String MESSAGES_URL = "https://ok.ru/messages";

    private final SelenideElement secondChat = $(By.xpath("//msg-chats-list/msg-chats-list-item[2]/a"));
    private final SelenideElement lastMessage =
        $(By.xpath("//msg-message[last()]//*[contains(@data-tsid, 'message_text')]"));
    private final SelenideElement firstChat = $(By.xpath("//msg-chats-list/msg-chats-list-item[1]/a"));
    private final SelenideElement messageInputField = $(By.xpath("//msg-chat//msg-input"));
    private final SelenideElement sendButton =
        $(By.xpath("//*[contains(@class, 'buttons-okmsg')]//button[@data-tsid='button_send']"));

    /**
     * Открыть первый чат в списке и получить последнее сообщение из него
     */
    public String getLastMessage() {
        secondChat.shouldBe(visible.because("Второй диалог в списке сообщений отсутствует")).click();
        return lastMessage.shouldBe(visible.because("Последнее сообщение в чате отсутствует")).getText();
    }

    /**
     * Открыть чат и отправить в него сообщение
     *
     * @param message текст сообщения для отправки
     */
    public MessagesPage writeMessageToEmptyChat(String message) {
        firstChat.shouldBe(visible.because("Требуемый чат для отправки сообщения отсутствует")).click();
        messageInputField.shouldBe(visible.because("Поле для ввода текста сообщения отсутствует")).sendKeys(message);
        sendButton.shouldBe(visible.because("Кнопка отправки сообщения отсутствует")).click();
        return this;
    }

    /**
     * Открыть чат и получить последнее сообщение после отправки
     */
    public String getLastMessageInEmptyChat() {
        firstChat.shouldBe(visible.because("Требуемый чат для проверки сообщения отсутствует")).click();
        return lastMessage.shouldBe(visible.because("Последнее сообщение в чате отсутствует")).getText();
    }

}
