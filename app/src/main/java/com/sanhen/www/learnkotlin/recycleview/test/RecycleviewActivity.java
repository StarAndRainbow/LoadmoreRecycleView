package com.sanhen.www.learnkotlin.recycleview.test;

import android.content.ClipData;
import android.support.annotation.MainThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sanhen.www.learnkotlin.R;

import java.util.ArrayList;
import java.util.List;


/**
 * recycleview分页加载
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * 洪洋https://blog.csdn.net/lmj623565791/article/details/51118836
 */
public class RecycleviewActivity extends AppCompatActivity {

    private static final int OTHER_TYPE = 1;
    private static final int ANTHER_TYPE = 2;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    RecycleviewAdapter mAdapter;

    SwipeRefreshLayout swipeRefreshlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        recyclerView = findViewById(R.id.rv);
        swipeRefreshlayout = findViewById(R.id.swipeRefreshlayout);
        recyclerView.addItemDecoration(new RecyclerViewDivider(this, RecyclerViewDivider.VERTICAL));
        recyclerView.setLayoutManager(linearLayoutManager = new LinearLayoutManager(this));
        mAdapter = new RecycleviewAdapter(createData(10), this);
        recyclerView.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                /**
                 * 模拟刷新
                 * */
                if(mAdapter.getData() >= 30){
                    mAdapter.changeState(1);
                    mAdapter.notifyItemChanged(mAdapter.getData());               //更新最后一项
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2 * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        RecycleviewActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.addDatas(createData((7)));
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }
        });


        recyclerView.setAdapter(mAdapter);


        swipeRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.changeState(0);
                mAdapter.setNewData(createData(10));
                mAdapter.notifyDataSetChanged();
                swipeRefreshlayout.setRefreshing(false);
            }
        });
    }


    /**
     * 创建模拟数据
     */
    public List<ItemData> createData(int size) {
        List<ItemData> data = new ArrayList<>();
        for (int i = 0; i < size / 2; i++) {
            data.add(new ItemData(new OtherData("aaa", "11"), 1));
        }

        for (int i = size / 2; i < size; i++) {
            data.add(new ItemData(new AnotherData("bbb"), 0));
        }
        return data;
    }


}
