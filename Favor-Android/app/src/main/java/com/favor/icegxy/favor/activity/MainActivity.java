package com.favor.icegxy.favor.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.favor.icegxy.favor.R;
import com.favor.icegxy.favor.utils.Constant;
import com.favor.icegxy.favor.widget.TouchPullView;

public class MainActivity extends AppCompatActivity {

    private float m_fTouchMoveStartY = 0;
    private MaterialRefreshLayout materialRefreshLayout;
    private ListView mCollectionListView;
    private static final String[] commonFunList = new String[]{

            "ListView控件演示",
            "ProgressBar控件演示",
            "GridView控件演示",
            "ScrollView控件演示",
            "DatePicker控件演示"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mCollectionListView = (ListView) findViewById(R.id.collection_ListView);
        materialRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh_Layout);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                //refreshing...
                // refresh complete
                materialRefreshLayout.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //load more refreshing...

                // load more refresh complete
                materialRefreshLayout.finishRefreshLoadMore();
            }
        });



    }
}
