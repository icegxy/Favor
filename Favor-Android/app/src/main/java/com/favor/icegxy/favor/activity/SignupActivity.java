package com.favor.icegxy.favor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.favor.icegxy.favor.R;
import com.favor.icegxy.favor.utils.Constant;

/**
 * Created by Icegxy on 2017/10/18.
 */

public class SignupActivity extends BaseActivity {

    private EditText usernameText;
    private EditText verificationCodeText;
    private EditText passwordText;
    private Button signupButton;

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
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }
}