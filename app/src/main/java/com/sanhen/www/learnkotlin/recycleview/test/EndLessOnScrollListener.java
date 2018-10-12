package com.sanhen.www.learnkotlin.recycleview.test;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by wnw on 16-5-26.
 */
public abstract class EndLessOnScrollListener extends  RecyclerView.OnScrollListener{

    //用于记录recyleview的布局管理器
    private LinearLayoutManager mLinearLayoutManager;

    //当前页，从0开始
    private int currentPage = 0;

    //已经加载出来的Item的数量
    private int totalItemCount;

    //主要用来存储上一个totalItemCount
    private int previousTotal = 0;

    //在屏幕上可见的item数量
    private int visibleItemCount;

    //在屏幕可见的Item中的第一个
    private int firstVisibleItem;

    //是否正在上拉数据
    private boolean loading = true;



    public EndLessOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();                                             //getChildCount();获取当前可见的item的数量
        totalItemCount = mLinearLayoutManager.getItemCount();                                        //getItemCount    getItemCount是所有item的数量
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();                      //获取第一个可见的item位置
        if(loading){
            Log.d("lx","firstVisibleItem: " +firstVisibleItem);
            Log.d("lx","totalPageCount:" +totalItemCount);
            Log.d("lx", "visibleItemCount:" + visibleItemCount);
            Log.d("lx","previousTotal:" + previousTotal);
            if(totalItemCount != previousTotal){                                                    //说明新的数据已经加载完成，如果没有加载完成 是相等的
                loading = false;
                previousTotal = totalItemCount;
            }
        }


        //这里需要好好理解                                                                                                      //10 + 10
        if(!loading && totalItemCount-visibleItemCount <= firstVisibleItem){                        //!loading 不在加载       &&  20 - 10 <= 10 （当数据不占满全屏的时候加载数据）
            currentPage ++;
            onLoadMore(currentPage);
            loading = true;
        }
    }


    /**
     * 页数从0开始，但
     * */
    public abstract void onLoadMore(int currentPage);



}



