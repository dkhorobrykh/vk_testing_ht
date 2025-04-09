package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

/**
 * Страница сообщений (<a href="https://ok.ru/messages">перейти</a>)
 * Позволяет получать и отправлять сообщения текущего пользователя
 * */
public class MessagesPage {

    public static final String MESSAGES_URL = "https://ok.ru/messages";

    private final SelenideElement firstMessage = $(By.xpath("//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[1]/msg" +
        "-chats-panel/div/msg-chats-list/msg-chats-list-item[1]"));
    private final SelenideElement lastMessageInChat = $(By.xpath(
        "//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[2]/msg-chat/div/section/div/msg-message-list/div/div[2]/msg" +
            "-message[last()]/div[1]/div/div/div"));
    private final SelenideElement emptyChat = $(By.xpath("//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[1]/msg" +
        "-chats-panel/div/msg-chats-list/msg-chats-list-item[1]"));
    private final SelenideElement messageInputField =
        $(By.xpath("//msg-input[@data-tsid=\"write_msg_input\"]//div" + "[@contenteditable=\"true\"]"));
    private final SelenideElement sendButton = $(By.xpath("//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[2]/msg" +
        "-chat/div/section/footer/msg-posting-form/div/form/msg-message-editor/div/aside[2]/slot/div/button[3]"));
    private final SelenideElement lastMessageInEmptyChat = $(By.xpath("//*[@id=\"msg_layer\"]/msg-app/div/msg-page" +
        "/div[2]/msg-chat/div/section/div/msg-message-list/div/div[2]/msg-message[2]"));

    /**
     * Открыть первый чат в списке и получить последнее сообщение из него
     * */
    public String getLastMessage() {
        firstMessage.shouldBe(visible.because("Первый диалог в списке сообщений отсутствует")).click();
        return lastMessageInChat.shouldBe(visible.because("Последнее сообщение в чате отсутствует")).getText();
    }

    /**
     * Открыть чат и отправить в него сообщение
     *
     * @param message текст сообщения для отправки
     * */
    public MessagesPage writeMessageToEmptyChat(String message) {
        emptyChat.shouldBe(visible.because("Требуемый чат для отправки сообщения отсутствует")).click();
        messageInputField.shouldBe(visible.because("Поле для ввода текста сообщения отсутствует")).setValue(message);
        sendButton.shouldBe(visible.because("Кнопка отправки сообщения отсутствует")).click();
        return this;
    }

    /**
     * Открыть чат и получить последнее сообщение после отправки
     * */
    public String getLastMessageInEmptyChat() {
        emptyChat.shouldBe(visible.because("Требуемый чат для проверки сообщения отсутствует")).click();
        return lastMessageInEmptyChat.shouldBe(visible.because("Последнее сообщение в чате отсутствует")).getText();
    }

}
