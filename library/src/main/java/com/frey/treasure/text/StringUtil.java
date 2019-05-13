package com.frey.treasure.text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isBlank(String text) {
        return text == null || text.trim().equals("");
    }

    public static boolean isNotBlank(String text) {
        return !isBlank(text);
    }

    public static List<String> getMatchTextList(String input, String regex) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    public static String getFirstMatchText(String input, String regex) {
        List<String> list = getMatchTextList(input, regex);
        if (list != null && list.size() > 0)
            return list.get(0);
        else
            return "";
    }

    public static boolean isMatch(String input, String regex) {
        return Pattern.matches(regex, input);
    }

    public static String extractText(String startStr, String endStr, String input) {
        int startIndex;
        int endIndex;
        if (isBlank(startStr)) {
            startIndex = 0;
        } else {
            int index = input.indexOf(startStr);
            if (index < 0)
                return "";

            if (index + startStr.length() > input.length() - 1)
                return "";

            startIndex = index + startStr.length();
        }

        if (isBlank(endStr)) {
            endIndex = input.length();
        } else {
            int index = input.indexOf(endStr);
            if (index < 0)
                return "";

            endIndex = index;
        }

        if (startIndex >= endIndex)
            return "";

        return input.substring(startIndex, endIndex);
    }
}
