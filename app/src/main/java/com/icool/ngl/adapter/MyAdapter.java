package com.icool.ngl.adapter;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.icool.ngl.App;
import com.icool.ngl.R;
import com.icool.ngl.bean.PostBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.sugarkawhi.ninegridlayout.NineGridAdapter;
import me.sugarkawhi.ninegridlayout.NineGridLayout;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/8/9.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private List<PostBean> mData;

    public MyAdapter(List<PostBean> data) {
        mData = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ngl, parent, false);
        return new MyHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final PostBean item = mData.get(position);
        holder.mTvTitle.setText(item.getTitle());
        holder.mTvContent.setText(item.getContent());
        holder.mNineGridLayout.setNineGridAdapter(new NineImageAdapter(item.getImgUrls()));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        NineGridLayout mNineGridLayout;
        TextView mTvTitle;
        TextView mTvContent;

        public MyHolder(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mTvContent = itemView.findViewById(R.id.tv_content);
            mNineGridLayout = itemView.findViewById(R.id.ngl);
        }
    }

    private static class NineImageAdapter extends NineGridAdapter {

        private List<String> mUrls;

        public NineImageAdapter(List<String> urls) {
            mUrls = urls;
        }

        @Override
        protected View getItemView(ViewGroup parent, int position) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_img, parent, false);
        }

        @Override
        protected void bindView(View view, int position) {
            String url = mUrls.get(position);
            ImageView imageView = view.findViewById(R.id.iv);
            Log.e("Adapter", url);
            Picasso.with(view.getContext())
                    .load(url)
                    .placeholder(R.drawable.ic_heart)
                    .into(imageView);
        }

        @Override
        protected int getItemCount() {
            return mUrls == null ? 0 : mUrls.size();
        }

        @Override
        protected void onItemClick(View view, int position) {
            super.onItemClick(view, position);
            Toast.makeText(App.getInstance(), "position " + position, Toast.LENGTH_SHORT).show();
        }
    }
}
