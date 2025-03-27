package tests.ui;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseUITest {

    // for github actions
    @BeforeAll
    public static void setUp() {
        Configuration.headless = true;
        Configuration.browser = "chrome";
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

}
