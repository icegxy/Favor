package com.favor.icegxy.favor.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.favor.icegxy.favor.R;
import com.favor.icegxy.favor.activity.base.BaseActivity;
import com.favor.icegxy.favor.constant.Constant;
import com.favor.icegxy.favor.presenter.SignupPresenter;
import com.favor.icegxy.favor.utils.HintUtil;
import com.favor.icegxy.favor.utils.VerifyCodeCountDownTimer;
import com.favor.icegxy.favor.utils.VerifyUtil;
import com.favor.icegxy.favor.view.SignupView;

/**
 * Created by Icegxy on 2017/10/18.
 */

public class SignupActivity extends BaseActivity implements SignupView {

    private final VerifyUtil verifyUtil = new VerifyUtil();
    private EditText usernameText;
    private EditText verificationCodeText;
    private EditText passwordText;
    private Button signupButton;
    private Button signinButton;
    private Button verificationCodeButton;
    private SignupPresenter signupPresenter;
    private ProgressDialog progressDialog;
    private final HintUtil hintUtil = new HintUtil();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupPresenter = new SignupPresenter(this);
        signupPresenter.attachView(this);
    }

    @Override
    public void initViews() {
        setContentView(R.layout.activity_signup);
        //为输入框设置提示语
        usernameText = (EditText) findViewById(R.id.editText_username);
        verificationCodeText = (EditText) findViewById(R.id.editText_verificationCode);
        passwordText = (EditText) findViewById(R.id.editText_password);
        signupButton = (Button) findViewById(R.id.button_signup);
        verificationCodeButton = (Button) findViewById(R.id.button_verificationCode);
        signinButton = (Button) findViewById(R.id.button_signinNow);
        progressDialog = new ProgressDialog(this);
        hintUtil.setHint(Constant.SIGNUP_PROMPT, 15, usernameText);
        hintUtil.setHint(Constant.VERIFICATIONCODE_PROMPT, 15, verificationCodeText);
        hintUtil.setHint(Constant.SIGNIN_PASSWD_PROMPT, 15, passwordText);
    }

    @Override
    public void initListeners() {
        signupButton.setOnClickListener(this);
        verificationCodeButton.setOnClickListener(this);
        signinButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_signup:
                signupPresenter.signup(usernameText.getText().toString(), verificationCodeText.getText().toString(), passwordText.getText().toString());
                break;
            case R.id.button_verificationCode:
                signupPresenter.getVerificationCode(usernameText.getText().toString());
                break;
            case R.id.button_signinNow:
                startActivity(SigninActivity.class, null);
                break;
        }
    }

    /**
     * 显示toast提示
     *
     * @param msg 提示内容
     */
    @Override
    public void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 更新界面
     */
    @Override
    public void updateView() {

    }

    /**
     * 显示加载图
     */
    @Override
    public void showLoading() {
        if (progressDialog != null) {
            progressDialog = ProgressDialog.show(this, "", "正在加载...", true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("正在加载...");
        }
        progressDialog.show();
    }

    /**
     * 隐藏加载图
     */
    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    /**
     * 显示倒计时
     */
    @Override
    public void showCountDownTimer() {
        new Runnable() {
            @Override
            public void run() {
                VerifyCodeCountDownTimer verifyCodeCountDownTimer = new VerifyCodeCountDownTimer(60000, 1000, verificationCodeButton);
                verifyCodeCountDownTimer.start();
            }
        }.run();
    }
}