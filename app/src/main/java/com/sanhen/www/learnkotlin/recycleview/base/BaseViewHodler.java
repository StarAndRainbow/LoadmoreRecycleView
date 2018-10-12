package com.sanhen.www.learnkotlin.recycleview.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;


/**
 * ViewHoder的作用:convertView.setTag与convertView进行绑定，然后当convertView复用时，直接
 * ViewHolder(getTag)中拿到convertView布局中的控件，省去了findViewById的时间
 * 实际上们每个convertView会绑定一个ViewHolder对象，这个viewHolder主要用于帮convertView存储布局中的控件
 * */

public class BaseViewHodler extends RecyclerView.ViewHolder {

    private View mConvertView;
    private  SparseArray<View> views;

    /**
     * 构造方法
     * @param itemView
     */
    public BaseViewHodler(@NonNull View itemView) {
        super(itemView);
        mConvertView = itemView;
        views = (SparseArray<View>) mConvertView.getTag();
    }

    /*********************************getView方法****************************************************/

    /**
     *
     * @param viewId        View的id
     * @param <T>           <T extends View>是声明这是一个泛型方法，同时extends View限制了返回的T类型必须是View的子类
     * @return              View的子类
     */
    public <T extends View> T getView(int viewId) {
        return obtainView(viewId);
    }


    public View getView(){
        return mConvertView;
    }

    /**
     * @param viewId    View的id
     * @param <T>
     * @return          View的子类
     */
    protected <T extends View> T obtainView(int viewId) {
                                                                                                      //TODO 2,构造方法中已经获取所有view的SparseArray
        if (null == views) {
            views = new SparseArray<>();
            mConvertView.setTag(views);                                                               //TODO 1，item里面的view设置为tag
        }

        /**
         * 从SparseArray中获取View，键值对保存，如果为null，则重新实列化
         * */
        View childView = views.get(viewId);
        if (null == childView) {
            childView = mConvertView.findViewById(viewId);
            views.put(viewId, childView);
        }
        return (T) childView;
    }




    /****************************************一些基本设置设置文本，设置颜色等等*********************************************************/
    /**
     * 设置文本View的文本
     * @param viewId
     * @param value
     * @return
     */
    public BaseViewHodler setText(int viewId, CharSequence value) {
        TextView view = obtainView(viewId);
        if (TextUtils.isEmpty(value)) {
            view.setText("");
        } else {
            view.setText(value);
        }
        return this;
    }




}
