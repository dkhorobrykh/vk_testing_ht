package tests.ui;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import pages.LoginPage;
import tests.TestType;

@Slf4j
public class NewsTest extends BaseUITest {

    @Test
    @DisplayName("Переход к последней новости должен загрузить список новых")
    @Tag(TestType.SLOW)
    @Timeout(value = 12, unit = TimeUnit.SECONDS)
    void appearanceOfNewsShouldBeEndless() {
        log.info("Авторизуемся с пользователем #1");
        var newsSection = new LoginPage()
            .enterLogin(FIRST_USER.getLogin())
            .enterPassword(FIRST_USER.getPassword())
            .login()
            .getNewsSection();

        log.info("Нажимаем Page END");
        newsSection.goToTheLastNews();

        log.info("Ожидаем появления новых новостей");
        newsSection
            .awaitNewNews()
            .await();
    }

}
