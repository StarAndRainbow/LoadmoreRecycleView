package com.sanhen.www.learnkotlin.recycleview.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

public class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        RecycleviewAdapter adapter = (RecycleviewAdapter) recyclerView.getAdapter();

        if (null == manager) {
            return;
        }

     /*   if (newState == RecyclerView.SCROLL_STATE_IDLE){
            if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange()) {
                Toast.makeText(recyclerView.getContext(),"滑动到底部",Toast.LENGTH_SHORT).show();
            }
        }*/


        if (manager instanceof LinearLayoutManager) {
            int lastVisibleItemPosition = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
            //判断是否还有更多数据加载       &&  显示的总数量     >    一页的数量      &&
            if (/*adapter.hasMore && adapter.getItemCount() > adapter.getPageCount() &&*/ adapter.getItemCount() - 1 == lastVisibleItemPosition) {
                /*if(!adapter.isLoading()){
                      adapter.LoadingMore();
                }*/
                Toast.makeText(recyclerView.getContext(), "滑动到底部", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
