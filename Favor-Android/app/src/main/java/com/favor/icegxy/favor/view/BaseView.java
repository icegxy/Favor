package com.favor.icegxy.favor.view;

/**
 * Created by icegxy on 2017/10/30.
 */

public interface BaseView {

    /**
     * 显示toast提示
     *
     * @param msg 提示内容
     */
    void showToast(String msg);

    /**
     * 更新界面
     */
    void updateView();

    /**
     * 显示加载图
     */
    void showLoading();

    /**
     * 隐藏加载图
     */
    void hideLoading();
}
