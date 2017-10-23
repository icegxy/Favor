package com.favor.icegxy.favor.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.favor.icegxy.favor.R;

public class MainActivity extends BaseActivity {

    private MaterialRefreshLayout materialRefreshLayout;
    private ListView mCollectionListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
