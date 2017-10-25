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

/**
 * Created by icegxy on 2017/10/18.
 */

public class SigninActivity extends BaseActivity {

    private EditText usernameText;
    private EditText passwordText;
    private Button signupButton;
    private Button signinButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //为输入框设置提示语
        usernameText = (EditText) findViewById(R.id.editText_username);
        setHint(Constant.SIGNIN_PROMPT, 15, usernameText);
        passwordText = (EditText) findViewById(R.id.editText_password);
        setHint(Constant.SIGNIN_PASSWD_PROMPT, 15, passwordText);

        signinButton = (Button) findViewById(R.id.button_signin);
        signinButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
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
                } else {
                    startActivity(intent);
                    finish();
                    return true;
                }
            }
        });

        signupButton = (Button) findViewById(R.id.button_signupNow);
        signupButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }


}