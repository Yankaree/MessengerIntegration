package me.ngcsonsplash.messengerintegration.death.environment;

import java.util.Random;

public class SuicideDeathMessages {

    private static final String[] MESSAGES = {
        "%player% đã tự kết liễu đời mình.",
        "%player% quyết định nghỉ game sớm.",
        "%player% không chịu nổi áp lực."
    };

    private static final Random RANDOM = new Random();

    public static String getRandom(String playerName) {
        return MESSAGES[RANDOM.nextInt(MESSAGES.length)]
                .replace("%player%", playerName);
    }
}
