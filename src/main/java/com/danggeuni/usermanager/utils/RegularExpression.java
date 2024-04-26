package com.danggeuni.usermanager.utils;

import java.util.regex.Pattern;

public class RegularExpression {
    public static final String PHONE = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
    public static final String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static Boolean checkEmail(String email) {
        return Pattern.matches(EMAIL, email);
    }

    public static Boolean checkPhone(String phoneNumber) {
        return Pattern.matches(PHONE, phoneNumber);
    }
}
