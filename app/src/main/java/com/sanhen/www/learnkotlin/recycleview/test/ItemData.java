package com.sanhen.www.learnkotlin.recycleview.test;

public class ItemData<T>{

    public T t;

    public int type;

    public ItemData(T t, int type) {
        this.t = t;
        this.type = type;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
