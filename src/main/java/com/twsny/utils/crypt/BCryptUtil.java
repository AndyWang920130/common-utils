package com.twsny.utils.crypt;

import org.mindrot.jbcrypt.BCrypt;


/**
 * 哈希加密 不可逆
 * BCrypt 密码加密
 */
public class BCryptUtil {
    // 默认强度（log rounds）
    private static final int DEFAULT_ROUNDS = 10;

    /**
     * 对明文密码进行加密
     *
     * @param plainPassword 明文
     * @return 哈希值
     */
    public static String hash(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(DEFAULT_ROUNDS));
    }

    /**
     * 校验密码
     *
     * @param plainPassword 明文密码
     * @param hashed        已经加密的密码
     * @return 是否匹配
     */
    public static boolean check(String plainPassword, String hashed) {
        if (plainPassword == null || hashed == null) return false;
        return BCrypt.checkpw(plainPassword, hashed);
    }

    public static void main(String[] args) {
        String rawData = "1231312414";
        String hashedData = BCryptUtil.hash(rawData);
        System.out.println("hashedData: " + hashedData);
        boolean flag = BCryptUtil.check(rawData, hashedData);
        System.out.println(flag);

        boolean flag2 = BCryptUtil.check("12313124141", hashedData);
        System.out.println(flag2);
    }

}
