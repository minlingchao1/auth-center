package com.lingchaomin.auth.server.common.utils;

import java.util.regex.Pattern;

/**
 * 电话工具
 *
 * @author : admin@chenlizhong.cn
 * @version : 1.0
 * @since : 2016-08-31 13:43.
 */
public class MobileUtil {

    private static final String[] CHINA_MOBILE = {"134", "135", "136", "137", "138", "139", "147", "150", "151", "152",
            "157", "158", "159", "182", "183", "184", "187", "188"};
    private static final String[] CHINA_UNION = {"130", "131", "132", "155", "156", "185", "186", "177"};
    private static final String[] CHINA_TELECOM = {"133", "153", "180", "181", "189"};

    /**
     * 国内手机号pattern
     */
    public static final Pattern MOBILE_PATTERN = Pattern.compile("(1[34578]{1}\\d{9})");


    /**
     * 检测国内手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile) {
        return MOBILE_PATTERN.matcher(mobile).matches();
    }

    private static boolean isType(String mobile, String[] mobiles) {
        for (String type : mobiles) {
            if (mobile.indexOf(type) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 移动
     *
     * @param mobile
     * @return
     */
    public static final boolean isChinaMobile(String mobile) {
        return checkMobile(mobile) ? isType(mobile, CHINA_MOBILE) : false;
    }

    /**
     * 联通
     *
     * @param mobile
     * @return
     */
    public static final boolean isChinaUnion(String mobile) {
        return checkMobile(mobile) ? isType(mobile, CHINA_UNION) : false;
    }

    /**
     * 电信
     *
     * @param mobile
     * @return
     */
    public static final boolean isChinaTelecom(String mobile) {
        return checkMobile(mobile) ? isType(mobile, CHINA_TELECOM) : false;
    }

}
