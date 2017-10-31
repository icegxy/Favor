package com.favor.icegxy.favor.utils;

import android.os.CountDownTimer;
import android.widget.Button;

import com.favor.icegxy.favor.constant.Constant;

/**
 * Created by icegxy on 2017/10/25.
 */

public class VerifyCodeCountDownTimer extends CountDownTimer {

    private Button verificationCodeButton;

    public VerifyCodeCountDownTimer(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        this.verificationCodeButton = button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        verificationCodeButton.setClickable(false);
        verificationCodeButton.setText(millisUntilFinished / 1000 + "s");
    }

    @Override
    public void onFinish() {
        verificationCodeButton.setText(Constant.GET_VERIFICATIONCODE_AGIAN);
        verificationCodeButton.setClickable(true);
    }


}
