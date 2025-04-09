package tests.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

@Slf4j
public class MessagesTest extends BaseUITest {

    private static final String expectedMessageText = "Мы обнаружили вход в ваш профиль";
    private static final String messageToSend = ">>> Test message <<<";

    @Test
    public void readMessageShouldReturnMessageText() {
        var loginPage = new LoginPage();

        log.info("Авторизируемся с пользователем #1");
        var profilePage = loginPage
            .open()
            .enterLogin(FIRST_USER_LOGIN)
            .enterPassword(FIRST_USER_PASSWORD)
            .login();

        log.info("Переходим на страницу сообщений");
        var messagesPage = profilePage
            .goToMessagesPage();

        log.info("Получаем текст последнего сообщения");
        String messageText = messagesPage.getLastMessage();

        log.info("Проверяем его соответствие ожидаемому");
        assertTrue(messageText.contains(expectedMessageText), "Неверный текст полученного сообщения");
    }

    @Test
    public void writeMessageShouldSendMessage() {
        var loginPage = new LoginPage();

        log.info("Авторизируемся с пользователем #1");
        var profilePage = loginPage
            .open()
            .enterLogin(FIRST_USER_LOGIN)
            .enterPassword(FIRST_USER_PASSWORD)
            .login();

        log.info("Переходим на страницу сообщений");
        var messagesPage = profilePage
            .goToMessagesPage();

        log.info("Отправляем сообщение в чате");
        messagesPage.writeMessageToEmptyChat(messageToSend);

        log.info("Проверяем, что сообщение успешно отправлено");
        assertTrue(messagesPage.getLastMessageInEmptyChat().contains(messageToSend), "Последнее сообщение не" +
            " содержит ожидаемое отправленное");
    }

}
