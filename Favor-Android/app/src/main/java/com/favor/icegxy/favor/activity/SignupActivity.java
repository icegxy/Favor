package com.favor.icegxy.favor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.favor.icegxy.favor.R;
import com.favor.icegxy.favor.utils.Constant;
import com.favor.icegxy.favor.utils.VerifyCodeCountDownTimer;
import com.favor.icegxy.favor.utils.VerifyUtil;

/**
 * Created by Icegxy on 2017/10/18.
 */

public class SignupActivity extends BaseActivity {

    private final VerifyUtil verifyUtil = new VerifyUtil();
    private EditText usernameText;
    private EditText verificationCodeText;
    private EditText passwordText;
    private Button signupButton;
    private Button signinButton;
    private Button verificationCodeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //为输入框设置提示语
        usernameText = (EditText) findViewById(R.id.editText_username);
        setHint(Constant.SIGNUP_PROMPT, 15, usernameText);
        verificationCodeText = (EditText) findViewById(R.id.editText_verificationCode);
        setHint(Constant.VERIFICATIONCODE_PROMPT, 15, verificationCodeText);
        passwordText = (EditText) findViewById(R.id.editText_password);
        setHint(Constant.SIGNIN_PASSWD_PROMPT, 15, passwordText);
        signupButton = (Button) findViewById(R.id.button_signup);

        signupButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                if (usernameText.getText().toString().equals(null) || usernameText.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), Constant.USERNAME_NECESSARY, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return false;
                } else if (passwordText.getText().toString().equals(null) || passwordText.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), Constant.PASSWD_NECESSARY, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return false;
                } else if (verificationCodeText.getText().toString().equals(null) || verificationCodeText.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), Constant.VERIFICATIONCODE_NECESSARY, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return false;
                } else {
                    startActivity(intent);
                    finish();
                    return true;
                }
            }
        });

        verificationCodeButton = (Button) findViewById(R.id.button_verificationCode);
        verificationCodeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (usernameText.getText().toString().equals(null) || usernameText.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), Constant.USERNAME_NECESSARY, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return false;
                } else if (!verifyUtil.isPhoneFormat(usernameText.getText().toString())) {
                    Toast toast = Toast.makeText(getApplicationContext(), Constant.PHONE_FORMAT_WRONG, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return false;
                } else {
                    new Runnable() {
                        @Override
                        public void run() {
                            VerifyCodeCountDownTimer verifyCodeCountDownTimer = new VerifyCodeCountDownTimer(60000, 1000, verificationCodeButton);
                            verifyCodeCountDownTimer.start();
                        }
                    }.run();
                }
                return false;
            }
        });

        signinButton = (Button) findViewById(R.id.button_signupNow);
        signinButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }
}