package utils;

import com.codeborne.selenide.SelenideElement;

/**
 * Класс, позволяющий валидировать загрузку страницы (использующий паттерн LoadableComponent)
 */
public abstract class LoadableComponent {

    public LoadableComponent(SelenideElement item) {
        validateComponent(item);
    }

    /**
     * Метод, позволяющий валидировать загрузку компонента
     */
    public abstract void validateComponent(SelenideElement item);
}
