package pages.guests;

import java.util.List;

public interface IGuestPage {

    /**
     * Получить список гостей
     *
     * @return список гостей
     */
    List<GuestWrapper> getAllGuests();
}
