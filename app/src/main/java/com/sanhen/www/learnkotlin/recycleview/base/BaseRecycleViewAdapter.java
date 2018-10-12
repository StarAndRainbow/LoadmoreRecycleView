package com.sanhen.www.learnkotlin.recycleview.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanhen.www.learnkotlin.R;

import java.util.List;


/**
 * 这边的T表示的都是ViewHolder，V为List<V>的数据类型
 */
public abstract class BaseRecycleViewAdapter<T extends BaseViewHodler, V> extends RecyclerView.Adapter<T> {

    protected Context mContext;
    protected List<V> mDatas;
    protected OnItemClickListener mOnItemClickListener;

    public BaseRecycleViewAdapter(Context context, List<V> data) {
        this.mContext = context;
        this.mDatas = data;
    }

    //TODO
    @NonNull
    @Override
    public abstract T onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);


    /**
     * 用于返回一个OnCreatViewHolder中需要的View
     */
    public View getCreateViewHolderView(int id, ViewGroup viewGroup) {
        return LayoutInflater.from(mContext).inflate(R.layout.layout_item, viewGroup, false);
    }


    /**
     * TODO 设置数据和设置点击事件
     */
    public void onBindViewHolder(@NonNull final T baseViewHodler, final int position) {
        baseViewHodler.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(baseViewHodler, position);
                    Log.e("position:","点击的item"+position);
                }
            }
        });

        convert(baseViewHodler, position);
    }

    /**
     * 设置数据
     *
     * @param baseViewHodler
     * @param position
     */
    public abstract void convert(@NonNull T baseViewHodler, int position);


    /**
     * TODO 返回数据的大小
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /***************************************点击事件******************************************************/
    public interface OnItemClickListener<T extends BaseViewHodler> {
        public void onItemClick(@NonNull final T baseViewHodler, final int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


}
