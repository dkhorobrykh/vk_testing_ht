package tests.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void testMain() {
        assertThat(true)
            .as("true is false... (☉_☉)")
            .isTrue();
    }

}
