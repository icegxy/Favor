package com.favor.icegxy.favor.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.favor.icegxy.favor.R;
import com.favor.icegxy.favor.utils.ViewPagerIndicator;

import java.util.ArrayList;

/**
 * Created by Icegxy on 2017/10/14.
 */

public class GuidePageActivity extends Activity{

    private ViewPager viewPager;
    private static int[] imgs = {R.drawable.guide_image1,R.drawable.guide_image2,R.drawable.guide_image3};
    private ArrayList<ImageView> imageViewArrayList = new ArrayList<ImageView>();
    private ImageView[] dotView;//小圆点
    private LinearLayout linearLayout;
    private Button buttonJump;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = findViewById(R.id.guide_ViewPager);
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(GuidePageActivity.this);
                imageView.setImageResource(imgs[position]);
                if(position == imgs.length - 1){
                    imageView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            Intent toMainActivity = new Intent(GuidePageActivity.this,MainActivity.class);
                            startActivity(toMainActivity);
                            return true;
                        }
                    });
                }
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                imageViewArrayList.add(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageViewArrayList.get(position));
            }

            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
        linearLayout = findViewById(R.id.dot_Layout);
        viewPager.setOnPageChangeListener(new ViewPagerIndicator(this,viewPager,linearLayout,3));

        buttonJump = findViewById(R.id.jump_Button);
        buttonJump.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent toMainActivity = new Intent(GuidePageActivity.this,MainActivity.class);
                startActivity(toMainActivity);
                return true;
            }
        });
    }
}
