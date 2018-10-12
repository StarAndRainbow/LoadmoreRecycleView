package com.sanhen.www.learnkotlin.recycleview.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sanhen.www.learnkotlin.R;

public class RecyclerViewAnotherHolder extends RecyclerView.ViewHolder {


    public TextView textView;


    public RecyclerViewAnotherHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv);
    }



}
