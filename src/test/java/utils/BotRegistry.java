package utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class BotRegistry {

    private final static String FIRST_USER_LOGIN = "79617542986";
    private final static String FIRST_USER_PASSWORD = "botForAutotests123!";
    private final static String FIRST_USER_NAME = "Иван Иванов";
    private final static String FIRST_USER_ID = "910117848542";

    private final static String SECOND_USER_LOGIN = "79223513163";
    private final static String SECOND_USER_PASSWORD = "botForAutotests123!";
    private final static String SECOND_USER_NAME = "Иван2 Иванов2";
    private final static String SECOND_USER_ID = "910109040312";

    private static final Map<String, TestBot> REGISTRY = new LinkedHashMap<>();

    static {
        registerBot(new TestBot(FIRST_USER_ID, FIRST_USER_LOGIN, FIRST_USER_PASSWORD, FIRST_USER_NAME));
        registerBot(new TestBot(SECOND_USER_ID, SECOND_USER_LOGIN, SECOND_USER_PASSWORD, SECOND_USER_NAME));
        registerBot(new TestBot("wrong", "wrong", "wrong", "wrong"));
    }

    public static void registerBot(TestBot bot) {
        if (REGISTRY.containsKey(bot.getLogin())) {
            throw new IllegalArgumentException("Bot with login " + bot.getLogin() + " is already registered.");
        }
        REGISTRY.put(
            bot.getLogin(),
            bot
        );
    }

    public static TestBot getBotByLogin(String login) {
        return REGISTRY.get(login);
    }

    public static TestBot getFirstBot() {
        return REGISTRY.get(FIRST_USER_LOGIN);
    }

    public static TestBot getSecondBot() {
        return REGISTRY.get(SECOND_USER_LOGIN);
    }

    public static TestBot getBotWithInvalidCredentials() {
        return REGISTRY.get("wrong");
    }
}
