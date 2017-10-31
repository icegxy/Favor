package com.favor.icegxy.favor.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by icegxy on 2017/10/25.
 * 验证公共类
 */

public class VerifyUtil {

    /**
     * 验证手机号码格式是否正确
     *
     * @param phoneNumber 手机号码
     * @return 格式是否正确
     */
    public final boolean isPhoneFormat(String phoneNumber) {
        Pattern pattern = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
