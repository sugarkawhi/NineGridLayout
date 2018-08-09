package com.icool.ngl.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.icool.ngl.R;
import com.icool.ngl.bean.PostBean;

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

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final PostBean item = mData.get(position);
        holder.mTvTitle.setText(item.getTitle() + position);
        holder.mTvContent.setText(item.getContent());
        holder.mNineGridLayout.setNineGridAdapter(new NineGridAdapter() {
            @Override
            protected View getItemView(ViewGroup parent, int position) {
                return LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_img, parent, false);
            }

            @Override
            protected void bindView(View view, int position) {
                int id = item.getResIds().get(position);
                ImageView imageView = view.findViewById(R.id.iv);
                imageView.setImageResource(id);
            }

            @Override
            protected int getItemCount() {
                return item.getResIds().size();
            }
        });
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
}
