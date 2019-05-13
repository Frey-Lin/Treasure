package com.frey.treasure.text;

public class PatternText {
    /**
     * 手机号码
     */
    public static final String PHONE_NUMBER = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    /**
     * 邮箱
     */
    public static final String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /**
     * 一个或多个中文字符
     */
    public static final String CHINESE = "[\\u4e00-\\u9fa5]+";
    /**
     * 18位身份证
     */
    public static final String ID_CARD_18 = "[1-8]\\d{5}[1-9]\\d{3}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[01])(\\d{4}|\\d{3}X)";
    /**
     * 15位身份证
     */
    public static final String ID_CARD_15 = "[1-8]\\d{5}\\d{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[01])(\\d{3})";
}
