package tests.ui;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;

/**
 * Базовый класс UI тестов
 */
@Timeout(value = 30, unit = TimeUnit.SECONDS)
public class BaseUITest {

    protected final static String BASE_URL = "https://ok.ru/";

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
     */
    @BeforeAll
    public static void setUp() {
        //        Configuration.headless = true;
        Configuration.browser = "chrome";
    }

    /**
     * Открытие начальной страницы перед каждым тестом
     */
    @BeforeEach
    public void setUpTest() {
        open(BASE_URL);
    }

    /**
     * Получение текущего URL страницы
     */
    public String getCurrentUrl() {
        return WebDriverRunner
            .getWebDriver()
            .getCurrentUrl();
    }

    /**
     * Проверяет, что текущий URL страницы совпадает с ожидаемым
     *
     * @param expectedUrl ожидаемый URL
     */
    public void assertCurrentUrlEquals(String expectedUrl) {
        var currentUrl = getCurrentUrl();
        assertThat(currentUrl)
            .as("URL текущей страницы должен совпадать с ожидаемым")
            .isEqualTo(expectedUrl);
    }

    /**
     * Закрытие драйвера после каждого теста
     */
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

}
