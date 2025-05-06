package tests.ui;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.LoginPage;
import pages.messages.MessagesPage;
import tests.TestType;

@Slf4j
public class MessagesTest extends BaseUITest {

    private static final String MESSAGE_TO_SEND = ">>> Test message <<<";

    @BeforeEach
    public void loginAndGoToMessagesPage() {
        log.info("Авторизуемся с пользователем #1");
        var profilePage = new LoginPage()
            .enterLogin(FIRST_USER.getLogin())
            .enterPassword(FIRST_USER.getPassword())
            .login();

        log.info("Переходим на страницу сообщений");
        profilePage.goToMessagesPage();
    }

    @Nested
    @DisplayName("При написании сообщений")
    @Tag(TestType.SLOW)
    public class WriteMessages {

        private static Stream<Arguments> getEmptyMessagesData() {
            return Stream.of(
                Arguments.of(""),
                Arguments.of("     "),
                Arguments.of("\n"),
                Arguments.of(" ")
            );
        }

        @Test
        @DisplayName("Написать сообщение в чат")
        public void writeMessageShouldSendMessage() {
            var messagesPage = new MessagesPage();

            log.info("Создаем пустой чат");
            messagesPage
                .getChatSection()
                .createNewEmptyChat();

            log.info("Отправляем сообщение в чате");
            messagesPage
                .getMessageSection()
                .sendMessage(MESSAGE_TO_SEND);

            log.info("Проверяем, что сообщение успешно отправлено");
            var allMessages = messagesPage
                .getMessageSection()
                .getAllMessagesInChat();
            assertThat(allMessages)
                .as("Сообщений в чате меньше требуемого")
                .hasSizeGreaterThanOrEqualTo(1);

            var lastMessageText = allMessages
                .getLast()
                .getText();
            assertThat(lastMessageText)
                .as("Последнее сообщение в чате должно содержать отправленное")
                .contains(MESSAGE_TO_SEND);
        }

        @ParameterizedTest
        @MethodSource("getEmptyMessagesData")
        @DisplayName("Проверка, что сообщения с пустым текстом не отправляются")
        public void writeMessageWithEmptyTextShouldNotSendIt(String message) {
            var messagesPage = new MessagesPage();

            log.info("Создаем пустой чат");
            messagesPage
                .getChatSection()
                .createNewEmptyChat();

            log.info("Отправляем проверочное сообщение в чате");
            messagesPage
                .getMessageSection()
                .sendMessage(MESSAGE_TO_SEND);

            log.info("Получаем последнее сообщение в чате");
            var allMessagesBeforeSend = messagesPage
                .getMessageSection()
                .getAllMessagesInChat();
            assertThat(allMessagesBeforeSend)
                .as("Сообщений в чате меньше требуемого")
                .hasSizeGreaterThanOrEqualTo(1);
            var lastMessageTextBeforeSend = allMessagesBeforeSend
                .getLast()
                .getText();

            log.info("Отправляем тестовое сообщение в чате");
            messagesPage
                .getMessageSection()
                .sendMessage(message);

            log.info("Проверяем, что сообщение не отправлено");
            var allMessagesAfterSend = messagesPage
                .getMessageSection()
                .getAllMessagesInChat();
            assertThat(allMessagesAfterSend)
                .as("Сообщений в чате меньше требуемого")
                .hasSizeGreaterThanOrEqualTo(1);
            var lastMessageTextAfterSend = allMessagesAfterSend
                .getLast()
                .getText();
            assertThat(lastMessageTextAfterSend)
                .as("Последнее сообщение в чате не должно содержать пустое сообщение")
                .isEqualTo(lastMessageTextBeforeSend);
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
            var messagesPage = new MessagesPage();

            log.info("Открываем первый чат в списке");
            messagesPage.openChat(1);

            log.info("Получаем текст последнего сообщения");
            var allMessages = messagesPage
                .getMessageSection()
                .getAllMessagesInChat();
            assertThat(allMessages)
                .as("Сообщений в чате меньше требуемого")
                .hasSizeGreaterThanOrEqualTo(1);
            String messageText = allMessages
                .getLast()
                .getText();

            log.info("Проверяем его соответствие ожидаемому");
            assertThat(messageText)
                .as("Последнее сообщение в чате не должно быть пустым")
                .isNotEmpty();
        }
    }
}
