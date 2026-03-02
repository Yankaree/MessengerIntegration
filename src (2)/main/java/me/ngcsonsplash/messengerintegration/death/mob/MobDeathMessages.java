package me.ngcsonsplash.messengerintegration.death.mob;

import java.util.Random;

public class MobDeathMessages {

    private static final Random random = new Random();

    private static final String[] MESSAGES = {

        "%victim% đã bị mob xử đẹp.",
        "%victim% không sống sót nổi trước quái vật.",
        "%victim% bị mob đánh hội đồng đến chết.",
        "%victim% vừa trở thành bữa ăn cho quái.",
        "%victim% bị mob cho ngỏm không thương tiếc.",
        "%victim% đã thua trước sức mạnh của quái vật.",
        "%victim% bị mob hành cho tới chết.",
        "%victim% không thể chạy thoát khỏi lũ quái.",
        "%victim% đã bị mob tiễn về sảnh.",
        "%victim% bị quái vật đánh gục.",
        "%victim% bị mob kết liễu trong bóng tối.",
        "%victim% vừa bị quái xử gọn.",
        "%victim% bị mob cho ăn hành no nê.",
        "%victim% bị quái vật dập tắt hy vọng sống.",
        "%victim% không qua nổi cửa ải mob.",
        "%victim% bị mob tấn công đến chết.",
        "%victim% bị lũ quái săn đuổi và hạ gục.",
        "%victim% đã bị mob bao vây và tiêu diệt.",
        "%victim% không đủ mạnh để đấu với quái vật.",
        "%victim% bị mob đánh úp bất ngờ.",
        "%victim% vừa trở thành chiến lợi phẩm của mob.",
        "%victim% bị quái vật đánh cho không kịp thở.",
        "%victim% đã bị mob đưa về màn hình respawn.",
        "%victim% không thoát khỏi nanh vuốt quái vật.",
        "%victim% bị mob kết thúc cuộc phiêu lưu.",
        "%victim% bị quái xử trong một nốt nhạc.",
        "%victim% vừa bị mob cho bay màu.",
        "%victim% bị quái vật áp đảo hoàn toàn.",
        "%victim% không sống sót qua đêm tối.",
        "%victim% bị mob tiễn về với ông bà."
    };

    public static String get(String victim) {
        return MESSAGES[random.nextInt(MESSAGES.length)]
                .replace("%victim%", victim);
    }
}
