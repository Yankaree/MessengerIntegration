package me.ngcsonsplash.messengerintegration.death.environment;

import java.util.Random;

public class ExplosionDeathMessages {

    private static final Random random = new Random();

    private static final String[] MESSAGES = {

        "%victim% đã bị nổ tung thành từng mảnh.",
        "%victim% đứng quá gần một vụ nổ.",
        "%victim% không sống sót sau vụ nổ kinh hoàng.",
        "%victim% bị thổi bay bởi một tiếng nổ lớn.",
        "%victim% vừa tan xác vì explosion.",
        "%victim% bị vụ nổ tiễn về sảnh.",
        "%victim% không kịp chạy khỏi tâm nổ.",
        "%victim% đã đánh giá thấp sức công phá.",
        "%victim% bị nổ cho bay màu.",
        "%victim% không thoát khỏi vụ nổ bất ngờ.",
        "%victim% vừa trở thành nạn nhân của thuốc nổ.",
        "%victim% bị vụ nổ kết liễu trong tích tắc.",
        "%victim% không đủ nhanh để tránh vụ nổ.",
        "%victim% đã bị thổi tung khỏi bản đồ.",
        "%victim% bị explosion xử gọn.",
        "%victim% bị nổ đến khi màn hình respawn xuất hiện.",
        "%victim% không sống sót giữa tâm chấn.",
        "%victim% bị sức ép vụ nổ nghiền nát.",
        "%victim% đã tan biến sau tiếng nổ.",
        "%victim% bị blast damage tiễn đi.",
        "%victim% không qua nổi bài test phản xạ với TNT.",
        "%victim% bị vụ nổ phá hủy hoàn toàn.",
        "%victim% vừa bị nổ cho lên bảng đếm số.",
        "%victim% không thể chịu nổi sức công phá.",
        "%victim% bị explosion dập tắt hy vọng sống.",
        "%victim% bị thổi bay không dấu vết.",
        "%victim% đã ở sai vị trí khi vụ nổ xảy ra.",
        "%victim% bị nổ tung trước khi kịp hiểu chuyện gì.",
        "%victim% vừa trở thành khói bụi sau vụ nổ.",
        "%victim% bị explosion đưa về màn hình respawn."
    };

    public static String get(String victim) {
        return MESSAGES[random.nextInt(MESSAGES.length)]
                .replace("%victim%", victim);
    }
}
