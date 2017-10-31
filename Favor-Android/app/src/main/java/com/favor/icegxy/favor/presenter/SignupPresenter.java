package com.favor.icegxy.favor.presenter;

import com.favor.icegxy.favor.constant.Constant;
import com.favor.icegxy.favor.model.User;
import com.favor.icegxy.favor.presenter.base.BasePresenter;
import com.favor.icegxy.favor.utils.HttpUtil;
import com.favor.icegxy.favor.utils.VerifyUtil;
import com.favor.icegxy.favor.view.SignupView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by icegxy on 2017/10/31.
 */

public class SignupPresenter extends BasePresenter {

    private String action = "signup";
    private SignupView signupView;
    public final VerifyUtil verifyUtil = new VerifyUtil();
    public User user;
    private final Gson gson = new Gson();

    public SignupPresenter(SignupView signupView) {
        this.signupView = signupView;
    }

    public void signup(String phoneNumber, String verificationCode, String passwd) {
        if (phoneNumber.equals(null) || phoneNumber.equals("")) {
            signupView.showToast(Constant.USERNAME_NECESSARY);
        } else if (!verifyUtil.isPhoneFormat(phoneNumber)) {
            signupView.showToast(Constant.PHONE_FORMAT_WRONG);
        } else if (verificationCode.equals(null) || verificationCode.equals("")) {
            signupView.showToast(Constant.VERIFICATIONCODE_NECESSARY);
        } else if (passwd.equals(null) || passwd.equals("")) {
            signupView.showToast(Constant.PASSWD_NECESSARY);
        } else {
            signupView.showLoading();
            Map<String, String> paraMap = new HashMap<String, String>();
            paraMap.put(phoneNumber, passwd);
            HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpRes() {
                @Override
                public void onSuccess(Object object) {
                    String json = object.toString();
                    user = gson.fromJson(json, User.class);
                    signupView.hideLoading();
                    signupView.updateView();
                }

                @Override
                public void onFail(String errMessage) {
                    signupView.showToast(errMessage);
                    signupView.hideLoading();
                }
            });
            httpUtil.sendPostHttp(Constant.BASE_URL + action, paraMap);
        }

    }

    public void getVerificationCode(String phoneNumber) {
        if (phoneNumber.equals(null) || phoneNumber.equals("")) {
            signupView.showToast(Constant.USERNAME_NECESSARY);
        } else if (!verifyUtil.isPhoneFormat(phoneNumber)) {
            signupView.showToast(Constant.PHONE_FORMAT_WRONG);
        } else {
            signupView.showCountDownTimer();
            Map<String, String> paraMap = new HashMap<String, String>();
            paraMap.put("phoneNumber", phoneNumber);
            HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpRes() {
                @Override
                public void onSuccess(Object object) {
                    String json = object.toString();
                    user = gson.fromJson(json, User.class);
                }

                @Override
                public void onFail(String errMessage) {
                    signupView.showToast(errMessage);
                }
            });
            httpUtil.sendPostHttp(Constant.BASE_URL + action, paraMap);
        }
    }
}
