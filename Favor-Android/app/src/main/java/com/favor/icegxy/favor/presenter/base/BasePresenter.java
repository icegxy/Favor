package com.favor.icegxy.favor.presenter.base;

import android.content.Context;

/**
 * Created by icegxy on 2017/10/30.
 */

public class BasePresenter {

    Context mContext;

    public void attachView(Context context) {
        this.mContext = context;
    }

    public void onCreate() {

    }

    public void onResume() {

    }

    public void onDestory() {
        mContext = null;
    }
}
