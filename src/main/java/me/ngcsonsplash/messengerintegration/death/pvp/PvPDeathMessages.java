package me.ngcsonsplash.messengerintegration.death.pvp;

import java.util.Random;

public class PvPDeathMessages {

    private static final Random random = new Random();

    private static final String[] MESSAGES = {

        "%victim% đã bị %killer% tiễn về sảnh.",
        "%victim% vừa bị %killer% cho ngỏm củ tỏi.",
        "%victim% không thể chống lại %killer%.",
        "%killer% đã kết liễu %victim% một cách tàn nhẫn.",
        "%victim% bị %killer% xử đẹp không thương tiếc.",
        "%killer% vừa tiễn %victim% lên bảng đếm số.",
        "%victim% bị %killer% cho về với ông bà.",
        "%killer% đã outplay hoàn toàn %victim%.",
        "%victim% bị %killer% đánh cho không kịp thở.",
        "%killer% vừa cho %victim% bay màu.",
        "%victim% bị %killer% đập không trượt phát nào.",
        "%killer% đã chứng minh ai mới là boss trước mặt %victim%.",
        "%victim% bị %killer% tiễn lên thiên đường Minecraft.",
        "%killer% khiến %victim% phải respawn ngay lập tức.",
        "%victim% vừa trở thành nạn nhân của %killer%.",
        "%killer% đã kết thúc chuỗi ngày sống của %victim%.",
        "%victim% không đủ trình để đấu với %killer%.",
        "%killer% cho %victim% một vé xem lại màn hình respawn.",
        "%victim% bị %killer% hành cho tới chết.",
        "%killer% đã cho %victim% nếm mùi thất bại.",
        "%victim% bị %killer% xử trong một nốt nhạc.",
        "%killer% tiễn %victim% đi mà không cần xin phép.",
        "%victim% đã bị %killer% dập tắt hy vọng sống.",
        "%killer% khiến %victim% biến mất khỏi bản đồ.",
        "%victim% vừa bị %killer% kết liễu gọn gàng.",
        "%killer% đã hạ gục %victim% một cách ngoạn mục.",
        "%victim% bị %killer% cho ăn hành no nê.",
        "%killer% vừa thêm một mạng vào bộ sưu tập từ %victim%.",
        "%victim% bị %killer% đánh bay mọi cơ hội sống sót.",
        "%killer% khiến %victim% phải trả giá bằng cả mạng sống."
    };

    public static String get(String victim, String killer) {

        String message = MESSAGES[random.nextInt(MESSAGES.length)];

        return message
                .replace("%victim%", victim)
                .replace("%killer%", killer);
    }
}
