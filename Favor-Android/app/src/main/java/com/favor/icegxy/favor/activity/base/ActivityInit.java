package com.favor.icegxy.favor.activity.base;

/**
 * Activity初始化公共抽象类
 * Created by icegxy on 2017/10/29.
 */

public interface ActivityInit {

    /**
     * 初始化布局
     */
    void initViews();

    /**
     * 增加按钮点击事件
     */
    void initListeners();
}
