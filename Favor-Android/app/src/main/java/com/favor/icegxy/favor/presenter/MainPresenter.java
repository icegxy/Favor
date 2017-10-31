package com.favor.icegxy.favor.presenter;

import com.favor.icegxy.favor.presenter.base.BasePresenter;
import com.favor.icegxy.favor.view.MainView;

/**
 * Created by icegxy on 2017/10/30.
 */

public class MainPresenter extends BasePresenter {

    public MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

}
