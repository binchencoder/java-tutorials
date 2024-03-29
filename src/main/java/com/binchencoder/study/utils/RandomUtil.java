package com.binchencoder.study.utils;

import java.util.Random;
import java.util.UUID;

/**
 * <p>
 * 随机数生成器工具类
 * </p>
 *
 * @author chenbin
 * @date 2022/05/27 16:32:38
 */
public class RandomUtil {

    /**
     * 用于随机选的字符和数字
     */
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 用于随机选的字符
     */
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 用于随机选的数字
     */
    public static final String NUMBERCHAR = "0123456789";

    private RandomUtil() {
        throw new AssertionError();
    }

    /**
     * 生成制定范围内的随机数
     *
     * @param scopeMin 最小数
     * @param scopeMax 最大数
     * @return 随机数
     */
    public static int integer(int scopeMin, int scopeMax) {
        Random random = new Random();
        return random.nextInt(scopeMax) % (scopeMax - scopeMin + 1) + scopeMin;
    }

    /**
     * 返回固定长度的数字
     *
     * @param length 长度
     * @return 随机数
     */
    public static String number(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String getString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String getMixString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String getLowerString(int length) {
        return getMixString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String getUpperString(int length) {
        return getMixString(length).toUpperCase();
    }

    /**
     * 生成一个定长的纯0字符串
     *
     * @param length 字符串长度
     * @return 纯0字符串
     */
    public static String getZeroString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     *
     * @param num      数字
     * @param fixedLen 字符串长度
     * @return 定长的字符串
     */
    public static String toFixedLenString(long num, int fixedLen) {
        StringBuilder sb = new StringBuilder();
        String strNum = String.valueOf(num);
        if (fixedLen - strNum.length() >= 0) {
            sb.append(getZeroString(fixedLen - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixedLen + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     *
     * @param num      数字
     * @param fixedLen 字符串长度
     * @return 定长的字符串
     */
    public static String toFixedLenString(int num, int fixedLen) {
        StringBuilder sb = new StringBuilder();
        String strNum = String.valueOf(num);
        if (fixedLen - strNum.length() >= 0) {
            sb.append(getZeroString(fixedLen - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixedLen + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 每次生成的len位数都不相同
     *
     * @param param
     * @return 定长的数字
     */
    public static int getNotSimple(int[] param, int len) {
        Random rand = new Random();
        for (int i = param.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result = result * 10 + param[i];
        }
        return result;
    }

    /**
     * 从指定的数组中随机数组中的某个元素
     *
     * @param param 指定的数组
     * @return 随机元素
     */
    public static <T> T randomItem(T[] param) {
        int index = integer(0, param.length);
        return param[index];
    }

    /**
     * 实现一个简单的字符串乘法
     *
     * @param str            字符串
     * @param multiplication 乘法数量
     * @return
     */
    @SuppressWarnings("unused")
    private static String strMultiplication(String str, int multiplication) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < multiplication; i++) {
            buffer.append(str);
        }
        return buffer.toString();
    }

    /**
     * 返回一个UUID编码 UUID:通用唯一识别码 (Universally Unique Identifier)
     *
     * @return 小写的UUID
     */
    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
            + s.substring(24);
    }

    /**
     * 返回一个UUID编码 UUID:通用唯一识别码 (Universally Unique Identifier)
     *
     * @return 大写的UUID
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        String temp = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
            + s.substring(19, 23) + s.substring(24);
        return temp.toUpperCase();
    }

    public static void main(String[] args) {
        int size = 3;
        for (int i = 0; i < size; i++) {
            System.out.println(getString(6));
        }
    }
}