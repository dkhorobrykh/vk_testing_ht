package tests.ui;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import com.codeborne.selenide.Configuration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;

/**
 * Базовый класс UI тестов
 * */
@Timeout(value = 30, unit = TimeUnit.SECONDS)
public class BaseUITest {

    protected final static String FIRST_USER_LOGIN = "79617542986";
    protected final static String FIRST_USER_PASSWORD = "botForAutotests123!";
    protected final static String FIRST_USER_NAME = "Иван Иванов";
    protected final static String FIRST_USER_ID = "910117848542";

    protected final static String SECOND_USER_LOGIN = "79223513163";
    protected final static String SECOND_USER_PASSWORD = "botForAutotests123!";
    protected final static String SECOND_USER_NAME = "Иван2 Иванов2";
    protected final static String SECOND_USER_ID = "910109040312";

    /**
     * Установка конфигурации
     * */
    @BeforeAll
    public static void setUp() {
        Configuration.headless = true;
        Configuration.browser = "chrome";
    }

    /**
     * Закрытие драйвера после каждого теста
     * */
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

}
