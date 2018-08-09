package me.sugarkawhi.ninegridlayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/8/9.
 */
public abstract class NineGridAdapter {

    protected abstract View getItemView(ViewGroup parent, int position);

    protected abstract void bindView(View view, int position);

    protected abstract int getItemCount();

    protected void onItemClick(Context context, View view, int position) {

    }
}
