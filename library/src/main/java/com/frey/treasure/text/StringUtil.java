package com.frey.treasure.text;

public class StringUtil {
    public static boolean isBlank(String text) {
        return text == null || text.trim().equals("");
    }

    public static boolean isNotBlank(String text) {
        return !isBlank(text);
    }
}
