package utils;

import static com.codeborne.selenide.Selenide.open;

import pages.ProfilePage;

public class UrlRedirector {

    public static final String PROFILE_URL = "https://ok.ru/profile/";
    public static final String BASE_URL = "https://ok.ru/";

    /**
     * Перейти на страницу конкретного пользователя по его ID
     *
     * @param profileId ID профиля для перехода
     * @return текущая страница профиля
     */
    public static ProfilePage goToProfilePage(String profileId) {
        open(UrlRedirector.PROFILE_URL + profileId);
        return new ProfilePage();
    }

    /**
     * Перейти на базовую страницу
     */
    public static void goToBasePage() {
        open(BASE_URL);
    }
}
