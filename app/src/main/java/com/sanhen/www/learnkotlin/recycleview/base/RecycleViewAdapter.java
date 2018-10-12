package com.sanhen.www.learnkotlin.recycleview.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;

import com.sanhen.www.learnkotlin.R;
import com.sanhen.www.learnkotlin.recycleview.test.EmptyUtil;

import java.util.List;

public class RecycleViewAdapter extends BaseRecycleViewAdapter<BaseViewHodler,String>{

    public RecycleViewAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public BaseViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        return new BaseViewHodler(getCreateViewHolderView(R.layout.layout_item,viewGroup));
    }


    @Override
    public void convert(@NonNull BaseViewHodler baseViewHodler, int position) {
        baseViewHodler.setText(R.id.tv,"数据为:"+EmptyUtil.checkString(mDatas.get(position)));
    }

}
