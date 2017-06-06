package com.lingchaomin.auth.server.common.utils;

import java.util.Date;
import java.util.Random;

/**
 * @author minlingchao
 * @date 2014-12-9 上午10:05:20
 * @description
 * @version
 */

public class RandomUtil {
    public static final long MIN_VALUE = 0x8000000000000000L;

    public static final long MAX_VALUE = 0x7fffffffffffffffL;

    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
            'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', };

    private static String toUnsignedString(long i, int shift) {
        char[] buf = new char[64];
        int charPos = 64;
        int radix = 1 << shift;
        long mask = radix - 1;
        do {
            buf[--charPos] = digits[(int) (i & mask)];
            i >>>= shift;
        } while (i != 0);
        return new String(buf, charPos, (64 - charPos));
    }

    // j为2的次方，如转成16进制就是4，32进制就是5...
    public static String getRand(long i, int j) {
        return toUnsignedString(i, j);
    }

    // 随机码＋时间戳＋随机码的生成
    public static Long getRand() {
        String str1, str2, str3;
        str1 = getRandStr(2);
        str3 = getRandStr(3);
        str2 = (new Date()).getTime() + "";
        // System.out.println(str1+str2+str3);
        return Long.parseLong(str1 + str2 + str3);
    }

    // 主键生成
    public static String getKey() {
        return getRand(getRand(), 6);
    }

    // 生成指定长度的随机串
    public static String getRandStr(Integer length) {
        String str = "";
        while (str.length() != length) {
            str = (Math.random() + "").substring(2, 2 + length);
        }
        return str;
    }

    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(RandomUtil.getRand());
        // System.out.println(RandomUtil.getRand(MAX_VALUE, 2));
    }
}
