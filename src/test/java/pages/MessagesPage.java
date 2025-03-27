package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class MessagesPage {

    public static final String MESSAGES_URL = "https://ok.ru/messages";

    private SelenideElement firstMessage = $(By.xpath("//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[1]/msg-chats" +
        "-panel/div/msg-chats-list/msg-chats-list-item[2]"));
    private SelenideElement lastMessageInChat = $(By.xpath("//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[2]/msg-chat" +
        "/div/section/div/msg-message-list/div/div[3]/msg-message"));
    private SelenideElement emptyChat = $(By.xpath("//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[1]/msg-chats" +
        "-panel/div/msg-chats-list/msg-chats-list-item[1]"));
    private SelenideElement messageInputField = $(By.xpath("//msg-input[@data-tsid=\"write_msg_input\"]//div" +
        "[@contenteditable=\"true\"]"));
    private SelenideElement sendButton = $(By.xpath("//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[2]/msg-chat/div" +
        "/section/footer/msg-posting-form/div/form/msg-message-editor/div/aside[2]/slot/div/button[3]"));
    private SelenideElement lastMessageInEmptyChat = $(By.xpath("//*[@id=\"msg_layer\"]/msg-app/div/msg-page/div[2" +
        "]/msg-chat/div/section/div/msg-message-list/div/div[2]/msg-message[2]"));

    public String getLastMessage() {
        firstMessage.shouldBe(visible);
        firstMessage.click();
        lastMessageInChat.shouldBe(visible);
        return lastMessageInChat.getText();
    }

    public MessagesPage writeMessageToEmptyChat(String message) {
        emptyChat.shouldBe(visible);
        emptyChat.click();
        messageInputField.shouldBe(visible);
        messageInputField.setValue(message);
        sendButton.shouldBe(visible);
        sendButton.click();
        return this;
    }

    public String getLastMessageInEmptyChat() {
        emptyChat.shouldBe(visible);
        emptyChat.click();
        lastMessageInEmptyChat.shouldBe(visible);
        return lastMessageInEmptyChat.getText();
    }

}
