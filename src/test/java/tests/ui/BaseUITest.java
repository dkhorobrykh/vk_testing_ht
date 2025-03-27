package tests.ui;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import org.junit.jupiter.api.AfterEach;

public class BaseUITest {

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

}
