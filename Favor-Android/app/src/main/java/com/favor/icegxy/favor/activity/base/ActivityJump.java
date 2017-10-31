package com.favor.icegxy.favor.activity.base;

import android.os.Bundle;

/**
 * Activity跳转封装
 * Created by icegxy on 2017/10/29.
 */

public interface ActivityJump {

    /**
     * 跳转至新界面
     *
     * @param jumpTo 跳转界面
     * @param bundle 参数
     */
    public void startActivity(Class<?> jumpTo, Bundle bundle);

    /**
     * 打开新界面，期待返回结果
     *
     * @param openClass   打开新界面
     * @param requestCode 请求码
     * @param bundle      参数
     */
    public void openActivityForResult(Class<?> openClass, int requestCode, Bundle bundle);

    /**
     * 返回上个界面
     *
     * @param bundle 参数
     */
    public void setResultOk(Bundle bundle);
}
