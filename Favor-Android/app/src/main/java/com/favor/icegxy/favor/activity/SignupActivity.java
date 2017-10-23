package com.favor.icegxy.favor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.favor.icegxy.favor.R;

/**
 * Created by Icegxy on 2017/10/18.
 */

public class SignupActivity extends BaseActivity {

    private EditText usernameText;
    private EditText verificationCodeText;
    private EditText passwordText;
    private String signinPrompt = "手机号";
    private String verificationCodePrompt = "验证码";
    private String passwdPrompt = "请输入密码";
    private Button signupButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameText = (EditText) findViewById(R.id.editText_username);
        SpannableString ssUsername = new SpannableString(signinPrompt);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        ssUsername.setSpan(ass, 0, ssUsername.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        usernameText.setHint(new SpannedString(ssUsername));

        verificationCodeText = (EditText) findViewById(R.id.editText_verificationCode);
        SpannableString ssverificationCode = new SpannableString(verificationCodePrompt);
        ssverificationCode.setSpan(ass, 0, ssverificationCode.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        verificationCodeText.setHint(new SpannedString(ssverificationCode));

        passwordText = (EditText) findViewById(R.id.editText_password);
        SpannableString ssPasswd = new SpannableString(passwdPrompt);
        ssPasswd.setSpan(ass, 0, ssPasswd.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        passwordText.setHint(new SpannedString(ssPasswd));

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