package pages;

import static com.codeborne.selenide.Selenide.$;

import utils.LoadableComponent;

public abstract class BasePage extends LoadableComponent {

    /**
     * Конструктор для вызова метода с валидацией прогрузки страницы
     */
    public BasePage() {
        super($("body"));
    }
}
