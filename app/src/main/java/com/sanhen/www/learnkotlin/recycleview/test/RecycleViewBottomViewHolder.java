package com.sanhen.www.learnkotlin.recycleview.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sanhen.www.learnkotlin.R;

public class RecycleViewBottomViewHolder extends RecyclerView.ViewHolder {
    public  TextView textView;
    public ProgressBar pb;


    public RecycleViewBottomViewHolder(@NonNull View itemView) {
        super(itemView);
        pb = itemView.findViewById(R.id.pb);
        textView = itemView.findViewById(R.id.footer_tv);
    }

}
