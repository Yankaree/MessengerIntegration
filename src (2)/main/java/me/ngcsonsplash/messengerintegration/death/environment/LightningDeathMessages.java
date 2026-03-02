package me.ngcsonsplash.messengerintegration.death.environment;

import java.util.Random;

public class LightningDeathMessages {

    private static final Random random = new Random();

    private static final String[] MESSAGES = {

        "%victim% đã bị sét đánh trúng.",
        "%victim% không may đứng dưới cơn giông.",
        "%victim% bị tia sét định mệnh kết liễu.",
        "%victim% vừa bị thiên nhiên xử đẹp.",
        "%victim% bị sét đánh cho bay màu.",
        "%victim% không sống sót sau cú sét kinh hoàng.",
        "%victim% đã trở thành cột thu lôi sống.",
        "%victim% bị trời cao gọi tên.",
        "%victim% bị sét tiễn về sảnh.",
        "%victim% không thoát khỏi cơn giông dữ dội.",
        "%victim% vừa trở thành nạn nhân của thời tiết xấu.",
        "%victim% bị sét đánh trong tích tắc.",
        "%victim% không kịp phản ứng trước tia sét.",
        "%victim% đã bị trời phạt.",
        "%victim% bị sét dập tắt hy vọng sống.",
        "%victim% không qua nổi thử thách của thiên nhiên.",
        "%victim% bị tia sét từ trên trời giáng xuống.",
        "%victim% vừa tan biến sau cú sét.",
        "%victim% bị sét đưa về màn hình respawn.",
        "%victim% không sống sót giữa cơn bão.",
        "%victim% đã bị giông tố kết thúc cuộc phiêu lưu.",
        "%victim% bị sét đánh không thương tiếc.",
        "%victim% vừa bị thiên nhiên cho ngỏm.",
        "%victim% không tránh được tia sét định mệnh.",
        "%victim% bị sét thiêu rụi trong chớp mắt.",
        "%victim% đã đánh giá thấp thời tiết.",
        "%victim% bị sét làm cho bốc hơi.",
        "%victim% vừa bị giông tố hạ gục.",
        "%victim% không thể chống lại sức mạnh của trời.",
        "%victim% đã bị sét phá hủy hoàn toàn."
    };

    public static String get(String victim) {
        return MESSAGES[random.nextInt(MESSAGES.length)]
                .replace("%victim%", victim);
    }
}
