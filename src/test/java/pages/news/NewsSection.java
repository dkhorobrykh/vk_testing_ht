package pages.news;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utils.LoadableComponent;
import utils.Promise;

/**
 * Обертка блока с новостями на главной странице {@link pages.ProfilePage}
 */
public class NewsSection extends LoadableComponent {

    private static final By SOME_NEWS = By.xpath(".//*[contains(@class, 'feed-w')]");
    private final SelenideElement item;

    public NewsSection(SelenideElement item) {
        super(item);
        this.item = item;
    }

    @Override
    public void validateComponent(SelenideElement item) {
        item.shouldBe(visible.because("Корневой элемент не прогрузился"));

        item
            .$$(SOME_NEWS)
            .shouldHave(CollectionCondition
                .sizeGreaterThan(0)
                .because("Список новостей пустой"));
    }

    /**
     * Перейти к последней новости путем нажатия на Page END
     */
    public void goToTheLastNews() {
        item.sendKeys(Keys.END);
    }


    /**
     * Ожидание появления новых новостей при переходе к последней
     *
     * @return список новостей с учетом загрузившихся
     */
    public Promise<List<NewsWrapper>> awaitNewNews() {
        return () -> {
            var currentNumber = getAllNews().size();

            item
                .$$(SOME_NEWS)
                .shouldHave(CollectionCondition
                    .sizeGreaterThan(currentNumber)
                    .because("Новые новости не появились"));

            return null;
        };
    }

    /**
     * Получить список видимых новостей
     *
     * @return список новостей
     */
    public List<NewsWrapper> getAllNews() {
        return item
            .$$(SOME_NEWS)
            .stream()
            .map(NewsWrapper::new)
            .toList();
    }
}
