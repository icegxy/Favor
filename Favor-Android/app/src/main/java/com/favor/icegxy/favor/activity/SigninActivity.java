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
import com.favor.icegxy.favor.presenter.SigninPersenter;
import com.favor.icegxy.favor.utils.HintUtil;
import com.favor.icegxy.favor.view.SigninView;

/**
 * Created by icegxy on 2017/10/18.
 */

public class SigninActivity extends BaseActivity implements SigninView {

    private EditText usernameText;
    private EditText passwordText;
    private Button signupButton;
    private Button signinButton;
    private ProgressDialog progressDialog;
    public SigninPersenter presenter;
    private final HintUtil hintUtil = new HintUtil();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SigninPersenter(this);
        presenter.attachView(this);
    }

    @Override
    public void initViews() {
        setContentView(R.layout.activity_signin);
        //为输入框设置提示语
        usernameText = (EditText) findViewById(R.id.editText_username);
        passwordText = (EditText) findViewById(R.id.editText_password);
        signinButton = (Button) findViewById(R.id.button_signin);
        signupButton = (Button) findViewById(R.id.button_signupNow);
        progressDialog = new ProgressDialog(this);
        hintUtil.setHint(Constant.SIGNIN_PROMPT, 15, usernameText);
        hintUtil.setHint(Constant.SIGNIN_PASSWD_PROMPT, 15, passwordText);
    }

    @Override
    public void initListeners() {
        signinButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_signin:
                presenter.signin(usernameText.getText().toString(), passwordText.getText().toString());
                break;
            case R.id.button_signupNow:
                startActivity(SignupActivity.class, null);
                break;
        }
    }

    @Override
    public void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void updateView() {

    }

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

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}