package com.sanhen.www.learnkotlin.recycleview.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanhen.www.learnkotlin.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 封装
 * https://blog.csdn.net/chenlove1/article/details/55106703
 * https://blog.csdn.net/lmj623565791/article/details/38902805#commentBox
 * 分页加载
 * https://www.jianshu.com/p/834f8fe25ca6
 * 显示不同布局，是建立了一个ItemData，里面包含了一个泛型和一个type。不是最后一项都是type，,最后一项返回 bottom
 * <p>
 * 问题1，通过显示两个不同布局，一个为item布局，一个位底部加载布局
 * 问题2，getItemCount返回数量+多一条底部加载布局，getItemViewType设置类型，最后一条返回的type为底布局，其他返回item的type
 */
public class RecycleviewAdapter extends RecyclerView.Adapter/*<RecyclerViewHolder>*/ {

    private List<ItemData> mData;
    private int NO_DATA = 1;
    private int LOAD_MORE = 0;

    private int footerStatus = LOAD_MORE;

    /**
     * 三种布局
     */
    public static final int TYPE_ANTOHER = 0;
    public static final int TYPE_OTHER = 1;
    public static final int TYPE_BOTTOM = 2;


    public RecycleviewAdapter(List<ItemData> data, Context context) {
        this.mData = data;
        footerStatus = LOAD_MORE;
    }


    /**
     * 创建ViewHodler
     * TYPE_ANTOHER -> AnotherData -> RecyclerViewHolder
     * TYPE_OTHER -> AnotherData -> RecyclerViewAnotherHolder
     */

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //底部布局
        if (TYPE_BOTTOM == viewType) {
            return new RecycleViewBottomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview_footer, viewGroup, false));
        }

        //another
        else if (TYPE_OTHER == viewType) {
            return new RecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false));
        } else {
            return new RecyclerViewAnotherHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_another, viewGroup, false));
        }
    }


    /**
     * 绑定数据
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        //底部
        if (TYPE_BOTTOM == getItemViewType(position)) {
            if(footerStatus == LOAD_MORE){
                ((RecycleViewBottomViewHolder) viewHolder).textView.setText("不要扯了···扯到d了");
                if(((RecycleViewBottomViewHolder) viewHolder).pb.getVisibility() == View.GONE)
                ((RecycleViewBottomViewHolder) viewHolder).pb.setVisibility(View.VISIBLE);
            }else if(footerStatus == NO_DATA){
                ((RecycleViewBottomViewHolder) viewHolder).textView.setText("没有更多数据了");
                ((RecycleViewBottomViewHolder) viewHolder).pb.setVisibility(View.GONE);
            }
        }

        //other布局
        else if (TYPE_OTHER == getItemViewType(position)){
            OtherData data = (OtherData) mData.get(position).getT();
            ((RecyclerViewHolder) viewHolder).textView.setText(data.name);
            ((RecyclerViewHolder) viewHolder).textView2.setText(data.age);
        }

        //another布局
        else {
            AnotherData data = (AnotherData) mData.get(position).getT();
            ((RecyclerViewAnotherHolder) viewHolder).textView.setText(data.str);
        }
    }

    /**
     * 这里返回的数据要多返回一条（最后一条用于显示底部）
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() + 1;
    }


    /**
     * 最后一项返回bottom，否则返回type
     */
    @Override
    public int getItemViewType(int position) {
        if (position == mData.size()) {
            return TYPE_BOTTOM;
        } else {
            return mData.get(position).getType();
        }
    }


    /**
     * @param datas 添加新的数据
     */
    public void addDatas(List<ItemData> datas) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(datas);
    }


    /**
     * 设置数据
     */
    public void setNewData(List<ItemData> data) {
        //重新设置数据，则表示可以加载更多

        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.clear();
        mData.addAll(data);
    }


    /**
     * 改变脚布局的状态的方法,在activity根据请求数据的状态来改变这个状态
     *
     * @param state
     */
    public void changeState(int state) {
        this.footerStatus = state;
    }

    public int getData(){
        return mData == null ? 0 : mData.size();
    }



}
