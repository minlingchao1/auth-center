package com.lingchaomin.auth.server.common.utils;



import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * 
 * @author lizhong.chen
 * @date 2014-7-10下午3:39:31
 * @description 短信工具
 * @version V1.0
 */
public class SmsUtil {
    public static AtomicInteger atomicInteger = new AtomicInteger(1);

    /**
     * 电话过滤
     */
    @SuppressWarnings("unused")
    public static final Pattern PHONE_PATTERN = Pattern.compile(
            "^([\\+][0-9]{1,3}([ \\.\\-]))?([\\(]{1}[0-9]{2,6}[\\)])?([0-9 \\.\\-/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$");

    public static final Pattern MOBILE_PATTERN = Pattern.compile("(1[34578]{1}\\d{9})");

    public static synchronized String get16Id() {
        int idsSize = 3;
        Calendar c = Calendar.getInstance();
        int year = c.get(1);
        int month = c.get(2) + 1;
        int day = c.get(5);
        int hour = c.get(11);
        int min = c.get(12);

        String smsIdStr = "" + year + ((month < 10) ? "0" + month : Integer.valueOf(month))
                + ((day < 10) ? "0" + day : Integer.valueOf(day)) + ((hour < 10) ? "0" + hour : Integer.valueOf(hour))
                + ((min < 10) ? "0" + min : Integer.valueOf(min));

        String idStr = String.valueOf(getIncrementId());
        int idSize = idStr.length();

        String zero = "";

        if (idSize < idsSize) {
            for (int i = 0; i < idsSize - idSize; ++i)
                zero = zero + "0";
        } else {
            idStr = idStr.substring(idSize - 3, idSize);
        }
        smsIdStr = smsIdStr + zero + idStr;

        return smsIdStr;
    }

    /**
     * 
     * 获取流水线号 --亿美p2p--19位长度
     * 
     * @param id
     * @return
     */
    public static long getSmsId(long id) {
        int idsSize = 6;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        String smsIdStr = "" + year + ((month < 10) ? "0" + month : month) + ((day < 10) ? "0" + day : day)
                + ((hour < 10) ? "0" + hour : hour) + ((min < 10) ? "0" + min : min);

        String idStr = String.valueOf(id);
        int idSize = idStr.length();

        // 补齐0
        String zero = "";
        //
        if (idSize < idsSize) {
            for (int i = 0; i < idsSize - idSize; i++) {
                zero += "0";
            }
        } else {
            idStr = idStr.substring(0, idsSize);
        }
        smsIdStr += zero + idStr;

        return Long.valueOf(smsIdStr);
    }

    public static String getSmsId() {
        int idsSize = 6;
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        String smsIdStr = "" + year + ((month < 10) ? "0" + month : month) + ((day < 10) ? "0" + day : day)
                + ((hour < 10) ? "0" + hour : hour) + ((min < 10) ? "0" + min : min);

        String idStr = String.valueOf(getIncrementId());
        int idSize = idStr.length();

        // 补齐0
        String zero = "";
        //
        if (idSize < idsSize) {
            for (int i = 0; i < idsSize - idSize; i++) {
                zero += "0";
            }
        } else {
            idStr = idStr.substring(0, idsSize);
        }
        smsIdStr += zero + idStr;

        return smsIdStr;
    }

    public static long getIncrementId() {
        int i = atomicInteger.incrementAndGet();
        if (i >= 1000000) {
            atomicInteger.set(1);
            return 1;
        }
        return i;
    }

    /**
     * 计算短信条数
     * 
     * @param content
     *            短信内容
     * @param oneSmsMaxLength
     *            单条短信最长长度 ，传0则取默认值67
     * @return
     */
    public static int countSms(String content, int oneSmsMaxLength) {
        int count = 0;

        if (StringUtils.isEmpty(content)) {
            return count;
        }

        if (oneSmsMaxLength == 0) {
            oneSmsMaxLength = 67;
        }

        int smsLength = content.length();
        count = smsLength / oneSmsMaxLength;

        if (smsLength % oneSmsMaxLength > 0) {
            count++;
        }

        return count;
    }

    public static int countSms(String content, int oneSmsMaxLength, int longSmsLength) {
        int count = 0;

        if (StringUtils.isEmpty(content)) {
            return count;
        }

        if (oneSmsMaxLength == 0) {
            oneSmsMaxLength = 70;
        }
        if (longSmsLength == 0) {
            longSmsLength = 67;
        }

        int smsLength = content.length();
        if (smsLength > oneSmsMaxLength) {
            count = smsLength / longSmsLength;
            if (smsLength % longSmsLength > 0) {
                count++;
            }
        } else {
            count = 1;
        }

        return count;
    }

    public static int countSms(String content) {
        return countSms(content, 0);
    }

    /**
     * 解析数据 获
     * 
     * @param mobileNumbers
     * @param line
     */
    public static List<String> parseMobile(String line) {

        List<String> mobileNumbers = new ArrayList<String>();

        String mayBeSplited = StringUtils.strip(StringUtils.strip(line, "'"), "\"").trim();

        mayBeSplited = mayBeSplited.replaceAll(" ", ",");

        String[] splited = mayBeSplited.split(",");

        for (String string : splited) {

            if (StringUtils.isEmpty(string) || !MOBILE_PATTERN.matcher(string).matches()) {
                continue;
            }

            mobileNumbers.add(string);
        }

        // 删除黑名单
        // mobileNumbers.removeAll(blackMobiles);

        return mobileNumbers;
    }

    /**
     * 解析数据 获
     * 
     * @param mobileNumbers
     * @param line
     */
    public static List<String> parseMobileWithInvaild(String line, List<String> invalidNumbers) {

        List<String> mobileNumbers = new ArrayList<String>();

        String mayBeSplited = StringUtils.strip(StringUtils.strip(line, "'"), "\"").trim();

        mayBeSplited = mayBeSplited.replaceAll(" ", ",");

        String[] splited = mayBeSplited.split(",");

        for (String string : splited) {
            if (StringUtils.isEmpty(string)) {
                continue;
            }

            if (!MOBILE_PATTERN.matcher(string).matches()) {
                invalidNumbers.add(string);
                continue;
            }

            mobileNumbers.add(string);
        }

        // 删除黑名单
        // mobileNumbers.removeAll(blackMobiles);

        return mobileNumbers;
    }

    public static boolean hasSign(String content) {
        int a = content.indexOf("【");
        int b = content.indexOf("】");
        if (a >= 0 && b > 0 && (a + 1) < b) {
            return true;
        }
        return false;
    }

}
