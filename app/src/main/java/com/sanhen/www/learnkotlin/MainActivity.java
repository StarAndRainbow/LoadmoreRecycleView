package com.sanhen.www.learnkotlin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sanhen.www.learnkotlin.recycleview.base.BaseRecycleViewAdapter;
import com.sanhen.www.learnkotlin.recycleview.base.BaseViewHodler;
import com.sanhen.www.learnkotlin.recycleview.base.RecycleViewAdapter;
import com.sanhen.www.learnkotlin.recycleview.test.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;



/**
 * 参考 https://blog.csdn.net/lmj623565791/article/details/38902805                             鸿洋
 * 参考 https://link.jianshu.com/?t=https://wangyantao.github.io/adapter/                       参考其中的一些说话
 * */
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecycleViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        recyclerView  = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecyclerViewDivider(this,RecyclerViewDivider.VERTICAL));
        recyclerView.setAdapter(mAdapter = new RecycleViewAdapter(this,createData(20)));
        mAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseViewHodler baseViewHodler, int position) {

            }
        });
    }


    public List<String> createData(int size){
        List<String> data = new ArrayList<>();
        for(int i = 0 ; i<size; i++){
            data.add("数据"+i);
        }
        return  data;
    }



}
