package com.favor.icegxy.favor.presenter;

import com.favor.icegxy.favor.constant.Constant;
import com.favor.icegxy.favor.model.User;
import com.favor.icegxy.favor.presenter.base.BasePresenter;
import com.favor.icegxy.favor.utils.HttpUtil;
import com.favor.icegxy.favor.utils.VerifyUtil;
import com.favor.icegxy.favor.view.SigninView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by icegxy on 2017/10/30.
 */

public class SigninPersenter extends BasePresenter {

    public String action = "login";
    public SigninView signinView;
    public User user;
    public final VerifyUtil verifyUtil = new VerifyUtil();
    private final Gson gson = new Gson();

    public SigninPersenter(SigninView signinView) {
        this.signinView = signinView;
    }

    public void signin(String phoneNumber, String passwd) {
        if (phoneNumber.equals(null) || phoneNumber.equals("")) {
            signinView.showToast(Constant.USERNAME_NECESSARY);
        } else if (!verifyUtil.isPhoneFormat(phoneNumber)) {
            signinView.showToast(Constant.PHONE_FORMAT_WRONG);
        } else if (passwd.equals(null) || passwd.equals("")) {
            signinView.showToast(Constant.PASSWD_NECESSARY);
        } else {
            signinView.showLoading();
            Map<String, String> paraMap = new HashMap<String, String>();
            paraMap.put(phoneNumber, passwd);
            HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpRes() {
                @Override
                public void onSuccess(Object object) {
                    String json = object.toString();
                    user = gson.fromJson(json, User.class);
                    signinView.hideLoading();
                    signinView.updateView();
                }

                @Override
                public void onFail(String errMessage) {
                    signinView.showToast(errMessage);
                    signinView.hideLoading();
                }
            });
            httpUtil.sendPostHttp(Constant.BASE_URL + action, paraMap);
        }
    }
}
