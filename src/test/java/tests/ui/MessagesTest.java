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
import pages.MessagesPage;
import tests.TestType;

@Slf4j
public class MessagesTest extends BaseUITest {

    private static final String expectedMessageText = "Мы обнаружили вход в ваш профиль";
    private static final String messageToSend = ">>> Test message <<<";

    @BeforeEach
    public void loginAndGoToMessagesPage() {
        log.info("Авторизируемся с пользователем #1");
        var profilePage = new LoginPage()
            .open()
            .enterLogin(FIRST_USER_LOGIN)
            .enterPassword(FIRST_USER_PASSWORD)
            .login();

        log.info("Переходим на страницу сообщений");
        profilePage.goToMessagesPage();
    }

    @Nested
    @DisplayName("При написании сообщений")
    @Tag(TestType.SLOW)
    public class WriteMessages {

        @Test
        @DisplayName("Написать сообщение в чат")
        public void writeMessageShouldSendMessage() {
            var messagesPage = new MessagesPage();

            log.info("Отправляем сообщение в чате");
            messagesPage.writeMessageToEmptyChat(messageToSend);

            log.info("Проверяем, что сообщение успешно отправлено");
            var lastMessageText = messagesPage.getLastMessageInEmptyChat();
            assertThat(lastMessageText)
                .as("Последнее сообщение в чате должно содержать отправленное")
                .contains(messageToSend);
        }

        @ParameterizedTest
        @MethodSource("getEmptyMessagesData")
        @DisplayName("Проверка, что сообщения с пустым текстом не отправляются")
        public void writeMessageWithEmptyTextShouldNotSendIt(String message) {
            var messagesPage = new MessagesPage();

            log.info("Получаем последнее сообщение в чате");
            var lastMessageTextBeforeSend = messagesPage.getLastMessageInEmptyChat();

            log.info("Отправляем сообщение в чате");
            messagesPage.writeMessageToEmptyChat(message);

            log.info("Проверяем, что сообщение не отправлено");
            var lastMessageTextAfterSend = messagesPage.getLastMessageInEmptyChat();
            assertThat(lastMessageTextAfterSend)
                .as("Последнее сообщение в чате не должно содержать пустое сообщение")
                .isEqualTo(lastMessageTextBeforeSend);
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
            var messagesPage = new MessagesPage();

            log.info("Получаем текст последнего сообщения");
            String messageText = messagesPage.getLastMessage();

            log.info("Проверяем его соответствие ожидаемому");
            assertThat(expectedMessageText)
                .as("Последнее сообщение в чате должно содержать ожидаемое")
                .contains(messageText);
        }
    }
}
