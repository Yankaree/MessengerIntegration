package me.ngcsonsplash.messengerintegration.death.environment;

import java.util.Random;

public class DrownDeathMessages {

    private static final Random random = new Random();

    private static final String[] MESSAGES = {

        "%victim% đã hết không khí dưới nước.",
        "%victim% quên mất cách thở khi bơi.",
        "%victim% bị ngạt nước đến chết.",
        "%victim% không thể trồi lên mặt nước kịp thời.",
        "%victim% chìm xuống đáy và không trở lại.",
        "%victim% đã ở dưới nước quá lâu.",
        "%victim% bị đại dương giữ lại mãi mãi.",
        "%victim% không thoát khỏi dòng nước.",
        "%victim% đã bị ngạt thở trong im lặng.",
        "%victim% chìm sâu cho đến khi màn hình respawn xuất hiện.",
        "%victim% không đủ oxy để tiếp tục.",
        "%victim% đã chọn ở lại dưới nước quá lâu.",
        "%victim% bị nước tiễn về sảnh.",
        "%victim% chìm xuống và mọi thứ kết thúc.",
        "%victim% bị ngạt giữa lòng biển.",
        "%victim% đã thử nín thở nhưng thất bại.",
        "%victim% bị nước nuốt chửng.",
        "%victim% không sống sót sau chuyến lặn dài.",
        "%victim% bị đại dương kết liễu.",
        "%victim% chìm xuống đáy sâu không lối thoát.",
        "%victim% không kịp tìm không khí.",
        "%victim% bị nước dập tắt sự sống.",
        "%victim% vừa hết hơi thở cuối cùng dưới nước.",
        "%victim% không qua nổi thử thách bơi lội.",
        "%victim% bị biển giữ lại mãi mãi.",
        "%victim% đã ngạt thở cho đến chết.",
        "%victim% bị nước đưa về màn hình respawn.",
        "%victim% không thể thoát khỏi mặt nước.",
        "%victim% chìm xuống và biến mất.",
        "%victim% đã để đại dương quyết định số phận."
    };

    public static String get(String victim) {
        return MESSAGES[random.nextInt(MESSAGES.length)]
                .replace("%victim%", victim);
    }
}
