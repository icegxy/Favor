package com.favor.icegxy.favor.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.EditText;

/**
 * Created by icegxy on 2017/10/31.
 */

public class HintUtil {

    public void setHint(String hint, int textSize, EditText editText) {
        SpannableString spannableString = new SpannableString(hint);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(textSize, true);
        spannableString.setSpan(ass, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setHint(new SpannedString(spannableString));
    }
}
