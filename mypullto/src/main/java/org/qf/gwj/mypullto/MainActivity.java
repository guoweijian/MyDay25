package org.qf.gwj.mypullto;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements Handler.Callback,PullToRefreshBase.OnRefreshListener{

    private PullToRefreshListView mPullToRefreshListView;
    private List<String > mData = new ArrayList<>();

    private Handler mHandler = new Handler(this);
    private ArrayAdapter<String> mAdapter;
    private ILoadingLayout mPullHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initPullLv();
    }

    private void initPullLv() {
        for (int i = 0; i < 100; i++) {
            mData.add(String.format("你好%s","android"+i));
        }
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,mData);
        mPullToRefreshListView.setAdapter(mAdapter);

        //设置监听
        mPullToRefreshListView.setOnRefreshListener(this);

        //设置头布局

    }

    private void initView() {
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pulllv);
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 200:
                mData.clear();
                for (int i = 0; i < 100; i++) {
                    //网址格式化
                    mData.add(String.format(Locale.CHINA,"第%d条数据%s",i,"小小的"));
                }
                //更新适配器


                break;
        }
        return false;
    }
}
