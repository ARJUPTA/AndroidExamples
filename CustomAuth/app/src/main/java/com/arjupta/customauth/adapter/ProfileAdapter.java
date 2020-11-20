//package com.arjupta.customauth.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.Bind;
//import example.com.retrofit.R;
//import example.com.retrofit.model.Profile;
//
///**
// * Created by michaellimantara on 20/3/16.
// */
//public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
//
//    private List<Profile> items = new ArrayList<Profile>();
//
//    public void setItems(List<Profile> items) {
//        this.items = items;
//    }
//
//    public void addItem(Profile item) {
//        items.add(item);
//    }
//
//    @Override
//    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ProfileViewHolder(parent);
//    }
//
//    @Override
//    public void onBindViewHolder(ProfileViewHolder holder, int position) {
//        Profile item = items.get(position);
//        holder.performBind(item, position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    static class ProfileViewHolder extends BaseViewHolder<Profile> {
//
//        @Bind(R.id.tv_initial)
//        TextView tvInitial;
//
//        @Bind(R.id.tv_name)
//        TextView tvName;
//
//        @Bind(R.id.tv_contact_no)
//        TextView tvContactNo;
//
//        @Bind(R.id.tv_email)
//        TextView tvEmail;
//
//        protected ProfileViewHolder(ViewGroup parent) {
//            super(parent, R.layout.item_profile);
//        }
//
//        @Override
//        protected void onBind(Profile item, int position) {
//            tvInitial.setText(item.getName().toUpperCase());
//            tvName.setText(item.getName());
//            tvContactNo.setText(item.getContactNo());
//            tvEmail.setText(item.getEmail());
//        }
//
//        @Override
//        protected void onClick(View v, Profile item) {
//
//        }
//    }
//}
