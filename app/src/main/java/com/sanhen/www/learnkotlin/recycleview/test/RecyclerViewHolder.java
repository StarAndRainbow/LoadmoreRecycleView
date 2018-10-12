package com.sanhen.www.learnkotlin.recycleview.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sanhen.www.learnkotlin.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    TextView textView2;


    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv);
        textView2 = itemView.findViewById(R.id.tv2);
    }

}
