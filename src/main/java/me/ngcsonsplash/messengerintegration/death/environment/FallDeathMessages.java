package me.ngcsonsplash.messengerintegration.death.environment;

import java.util.Random;

public class FallDeathMessages {

    private static final Random random = new Random();

    private static final String[] MESSAGES = {

        "%victim% đã rơi từ trên cao xuống và không sống sót.",
        "%victim% quên mang dù khi nhảy từ trên cao.",
        "%victim% đáp đất quá mạnh và phải trả giá.",
        "%victim% đã thử bay nhưng thất bại.",
        "%victim% rơi tự do cho đến khi gặp mặt đất.",
        "%victim% đã va chạm với mặt đất quá mạnh.",
        "%victim% không tính toán đúng độ cao.",
        "%victim% nhảy xuống và không thể đứng dậy nữa.",
        "%victim% bị trọng lực xử đẹp.",
        "%victim% đã đánh giá thấp độ cao.",
        "%victim% rơi xuống như một thiên thạch.",
        "%victim% đáp đất theo cách không mong muốn.",
        "%victim% thử parkour nhưng thất bại.",
        "%victim% đã kết thúc chuyến bay không vé khứ hồi.",
        "%victim% bị mặt đất từ chối nhẹ nhàng.",
        "%victim% rơi xuống và mọi thứ kết thúc.",
        "%victim% không qua nổi bài test trọng lực.",
        "%victim% đã chạm đất hơi quá mạnh.",
        "%victim% thử nhảy mà quên mất độ cao.",
        "%victim% bị mặt đất tiễn về sảnh.",
        "%victim% không thể chống lại trọng lực.",
        "%victim% rơi xuống và tan nát hy vọng.",
        "%victim% đã bay nhưng không hạ cánh an toàn.",
        "%victim% thử làm chim nhưng không có cánh.",
        "%victim% rơi tự do đến khi màn hình respawn xuất hiện.",
        "%victim% đã chọn cách đáp đất đầy đau đớn.",
        "%victim% nhảy quá tự tin và trả giá.",
        "%victim% đã gặp mặt đất trong tích tắc.",
        "%victim% không sống sót sau cú rơi định mệnh.",
        "%victim% bị trọng lực đưa về màn hình respawn."
    };

    public static String get(String victim) {
        return MESSAGES[random.nextInt(MESSAGES.length)]
                .replace("%victim%", victim);
    }
}
