/*
 *  Copyright 2016 Eric Liu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.liuguangqiang.support.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ValidateUtils
 * <p>
 * Created by Eric on 2014-5-19.
 */
public class ValidateUtils {

    private ValidateUtils() {
    }

    public static boolean isMobileNumber(String str) {
        String reg = "^(13[0-9]|15[0-9]|18[7|8|9|6|5])\\d{4,8}$";
        return validate(reg, str);
    }

    public static boolean isEmail(String str) {
        String reg =
                "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]*@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return validate(reg, str);
    }

    public static boolean isNumeric(String str) {
        return validate("^[0-9]+$", str);
    }

    public static boolean isNumAndAlphabet(String str) {
        return validate("^[A-Za-z0-9]+$", str);
    }

    public static boolean isAlphabet(String str) {
        return validate("^[A-Za-z]+$", str);
    }

    public static boolean isUrl(String str) {
        String reg = "(http(s)?://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        return validate(reg, str);
    }

    public static boolean containChinese(String str) {
        int count = 0;
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count++;
            }
        }

        return count > 0;
    }

    public static boolean validate(String expression, String str) {
        if (str == null) return false;
        Pattern p = Pattern.compile(expression);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
