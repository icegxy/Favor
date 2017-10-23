package com.favor.icegxy.favor.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.favor.icegxy.favor.R;

/**
 * Created by icegxy on 2017/10/14.
 */

public class WelcomeActivity extends Activity implements Runnable {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //启动一个延迟线程
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            //延迟1秒时间
            Thread.sleep(2000);
            SharedPreferences preferences = getSharedPreferences("count", 0); // 存在则打开它，否则创建新的Preferences
            int count = preferences.getInt("count", 0); // 取出数据

            //如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
            if (count == 0) {
                Intent intent1 = new Intent();
                intent1.setClass(WelcomeActivity.this, GuidePageActivity.class);
                startActivity(intent1);
            } else {
                Intent intent2 = new Intent();
                intent2.setClass(WelcomeActivity.this, SigninActivity.class);
                startActivity(intent2);
            }
            finish();

            //实例化Editor对象
            SharedPreferences.Editor editor = preferences.edit();
            //存入数据
            editor.putInt("count", 1); // 存入数据
            //提交修改
            editor.apply();
        } catch (InterruptedException e) {

        }
    }
}
