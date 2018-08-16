package me.sugarkawhi.ninegridlayout;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhzy
 * Created by zhzy on 2018/8/9.
 */
public abstract class NineGridAdapter {

    protected abstract View getItemView(ViewGroup parent, int position);

    protected abstract void bindView(View view, int position);

    protected abstract int getItemCount();

    protected void onItemClick(View view, int position) {
    }


}
