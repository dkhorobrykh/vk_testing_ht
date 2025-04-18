package utils;

import com.codeborne.selenide.SelenideElement;

/**
 * Класс, позволяющий валидировать полноценную загрузку страницы (использующий паттерн LoadableComponent)
 */
public abstract class LoadableComponent {

    private final SelenideElement item;

    public LoadableComponent(SelenideElement item) {
        this.item = item;
        validateComponent(item);
    }

    /**
     * Метод, позволяющий валидировать полную загрузку компонента
     */
    public abstract void validateComponent(SelenideElement item);
}
