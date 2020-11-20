//package com.arjupta.customauth.adapter;
//
//import android.support.annotation.LayoutRes;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import butterknife.ButterKnife;
//
///**
// * Created by michaellimantara on 20/3/16.
// */
//public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//    private T item;
//
//    protected BaseViewHolder(ViewGroup parent, @LayoutRes int itemLayoutId) {
//        super(LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false));
//
//        ButterKnife.bind(this, itemView);
//        itemView.setOnClickListener(this);
//    }
//
//    protected void performBind(T item, int position) {
//        this.item = item;
//        onBind(item, position);
//    }
//
//    @Override
//    public void onClick(View v) {
//        onClick(v, item);
//    }
//
//    protected abstract void onBind(T item, int position);
//    protected abstract void onClick(View v, T item);
//}
