package me.ngcsonsplash.messengerintegration.death.mob;

import java.util.Random;

public class CreeperDeathMessages {

    private static final Random random = new Random();

    private static final String[] MESSAGES = {

        "%victim% đã bị Creeper cho nổ tung.",
        "%victim% đứng quá gần Creeper và trả giá bằng mạng sống.",
        "%victim% không kịp chạy khỏi tiếng xì xì định mệnh.",
        "%victim% bị Creeper tiễn về sảnh bằng một cú nổ.",
        "%victim% vừa nổ tung cùng Creeper.",
        "%victim% bị Creeper cho bay màu không báo trước.",
        "%victim% không sống sót sau vụ nổ của Creeper.",
        "%victim% nghe tiếng xì... và thế là hết.",
        "%victim% bị Creeper cho lên bảng đếm số.",
        "%victim% bị Creeper nổ cho tan xác.",
        "%victim% không thoát khỏi vụ nổ bất ngờ.",
        "%victim% đã đánh giá thấp một Creeper.",
        "%victim% bị Creeper phục kích và cho nổ tung.",
        "%victim% vừa trở thành nạn nhân của tiếng xì xì.",
        "%victim% bị Creeper kết liễu trong tích tắc.",
        "%victim% không đủ nhanh để thoát khỏi Creeper.",
        "%victim% bị Creeper tiễn đi bằng bom tự chế.",
        "%victim% bị nổ banh xác bởi Creeper.",
        "%victim% đã bị Creeper đưa về màn hình respawn.",
        "%victim% không tránh được cú nổ định mệnh.",
        "%victim% bị Creeper xử gọn bằng một tiếng nổ.",
        "%victim% vừa bị Creeper cho ăn TNT miễn phí.",
        "%victim% bị Creeper áp sát và kích nổ.",
        "%victim% không qua nổi bài test phản xạ của Creeper.",
        "%victim% bị Creeper làm cho bốc hơi.",
        "%victim% vừa tan biến sau tiếng nổ.",
        "%victim% bị Creeper biến thành bụi.",
        "%victim% không kịp nói lời cuối cùng trước vụ nổ.",
        "%victim% bị Creeper cho nổ không trượt phát nào.",
        "%victim% đã bị Creeper phá hủy hoàn toàn."
    };

    public static String get(String victim) {
        return MESSAGES[random.nextInt(MESSAGES.length)]
                .replace("%victim%", victim);
    }
}
