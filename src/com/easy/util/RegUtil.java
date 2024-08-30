package com.easy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName RegUtil
 * @Description 正则表达式验证工具类
 * @Author MLY
 * @Date 2024/6/14 10:46
 * @Version 1.0
 */

//正则表达式验证工具类
public class RegUtil {
    public static boolean validatePassword(String... passwords) {
        //是否包含大写字母、是否包含特殊字符、是否包含数字、长度是否为6-16之间
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9])";
        for (String password : passwords) {
            if (password == null || password.length() < 6 || password.length() > 16)
                return false;
            // Check for at least one uppercase letter, one digit and one special character
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(password);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }
    private static final String REG_6_16 = "^\\w{2,16}$";

    public static boolean test(String reg, String str) {
        return str.matches(reg);
    }

    public static boolean test6_16(String... str) {
        if (str == null || str.length == 0) return false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == null || !str[i].matches(REG_6_16)) return false;
        }
        return true;
    }
}
