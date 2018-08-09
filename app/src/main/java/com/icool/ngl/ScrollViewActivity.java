package com.icool.ngl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/8/9.
 */
public class ScrollViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        setTitle("IN SCROLLVIEW");
    }
}
