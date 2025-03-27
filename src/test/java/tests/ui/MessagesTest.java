package tests.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MessagesPage;
import pages.ProfilePage;

public class MessagesTest extends BaseUITest{

    private LoginPage loginPage = new LoginPage();
    private ProfilePage profilePage = new ProfilePage();
    private MessagesPage messagesPage = new MessagesPage();

    private static final String userLogin = System.getenv("FIRST_USER_LOGIN");
    private static final String userPassword = System.getenv("FIRST_USER_PASSWORD");

    @Test
    public void readMessage_shouldReturnMessageText() {
        // authorize
        profilePage = loginPage
            .open()
            .enterLogin(userLogin)
            .enterPassword(userPassword)
            .login();

        // go to the messages page
        messagesPage = profilePage
            .goToMessagesPage();

        // read the last message
        String messageText = messagesPage.getLastMessage();

        // check that the message text is correct
        assertTrue(messageText.contains("Мы обнаружили вход в ваш профиль"));
    }

    @Test
    public void writeMessage_shouldSendMessage() {
        // authorize
        profilePage = loginPage
            .open()
            .enterLogin(userLogin)
            .enterPassword(userPassword)
            .login();

        // go to the messages page
        messagesPage = profilePage
            .goToMessagesPage();

        // write a message
        messagesPage.writeMessageToEmptyChat(">>> Test message <<<");

        // check that the message is sent
        assertTrue(messagesPage.getLastMessageInEmptyChat().contains(">>> Test message <<<"));
    }

}
