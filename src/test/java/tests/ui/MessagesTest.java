package tests.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.LoginPage;
import tests.TestType;

@Slf4j
public class MessagesTest extends BaseUITest {

    private static final String expectedMessageText = "Мы обнаружили вход в ваш профиль";
    private static final String messageToSend = ">>> Test message <<<";

    @Nested
    @DisplayName("При написании сообщений")
    @Tag(TestType.SLOW)
    public class WriteMessages {

        @Test
        @DisplayName("Написать сообщение в чат")
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

        @ParameterizedTest
        @MethodSource("getEmptyMessagesData")
        @DisplayName("Проверка, что сообщения с пустым текстом не отправляются")
        public void writeMessageWithEmptyTextShouldNotSendIt(String message) {
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

            log.info("Получаем последнее сообщение в чате");
            var lastMessageText = messagesPage.getLastMessageInEmptyChat();

            log.info("Отправляем сообщение в чате");
            messagesPage.writeMessageToEmptyChat(message);

            log.info("Проверяем, что сообщение не отправлено");
            assertEquals(lastMessageText, messagesPage.getLastMessageInEmptyChat(), "В чат отправилось пустое сообщение");
        }

        private static Stream<Arguments> getEmptyMessagesData() {
            return Stream.of(
                Arguments.of(""),
                Arguments.of("     "),
                Arguments.of("\n"),
                Arguments.of(" ")
            );
        }
    }

    @Nested
    @DisplayName("При прочтении сообщений")
    @Tag(TestType.FAST)
    public class ReadMessages {
        @Test
        @Tag(TestType.FAST)
        @DisplayName("Прочитать сообщение из чата")
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
    }
}
