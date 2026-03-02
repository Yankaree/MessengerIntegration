package me.ngcsonsplash.messengerintegration.death.environment;

import java.util.Random;

public class FireDeathMessages {

    private static final Random random = new Random();

    private static final String[] MESSAGES = {

        "%victim% đã bị thiêu rụi bởi ngọn lửa.",
        "%victim% chơi với lửa và phải trả giá.",
        "%victim% bị cháy đến khi không còn gì.",
        "%victim% vừa trở thành tro bụi.",
        "%victim% bị lửa thiêu sống.",
        "%victim% không sống sót giữa biển lửa.",
        "%victim% bị lửa nuốt chửng.",
        "%victim% đã quá gần dung nham.",
        "%victim% rơi vào lava và tan chảy.",
        "%victim% bị magma đốt cháy không thương tiếc.",
        "%victim% không thoát khỏi ngọn lửa dữ dội.",
        "%victim% bị thiêu đốt cho đến chết.",
        "%victim% vừa bốc hơi trong biển lava.",
        "%victim% bị lửa tiễn về sảnh.",
        "%victim% đã đánh giá thấp sức nóng.",
        "%victim% bị cháy đến khi màn hình respawn xuất hiện.",
        "%victim% không đủ nhanh để dập lửa.",
        "%victim% bị dung nham nuốt chửng hoàn toàn.",
        "%victim% đã tự biến mình thành đuốc sống.",
        "%victim% bị lửa kết thúc cuộc phiêu lưu.",
        "%victim% bị đốt cháy trong tích tắc.",
        "%victim% đã tan biến giữa biển lửa.",
        "%victim% bị thiêu rụi không còn dấu vết.",
        "%victim% vừa bị lửa cho bay màu.",
        "%victim% không thể chịu nổi sức nóng.",
        "%victim% bị lửa dập tắt hy vọng sống.",
        "%victim% vừa hóa thành than.",
        "%victim% bị lửa thiêu cho đến hơi thở cuối cùng.",
        "%victim% không sống sót sau cơn hỏa hoạn.",
        "%victim% đã bị ngọn lửa định mệnh kết liễu."
    };

    public static String get(String victim) {
        return MESSAGES[random.nextInt(MESSAGES.length)]
                .replace("%victim%", victim);
    }
}
