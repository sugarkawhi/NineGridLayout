package com.icool.ngl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.icool.ngl.adapter.MyAdapter;
import com.icool.ngl.bean.PostBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/8/9.
 */
public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        setTitle("IN RECYCLERVIEW");
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        init();
    }

    private void init() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.ic_cat);
        list.add(R.drawable.ic_bird);
        list.add(R.drawable.ic_horse);
        list.add(R.drawable.ic_deef);
        list.add(R.drawable.ic_dog);
        list.add(R.drawable.ic_fox);
        list.add(R.drawable.ic_heart);
        list.add(R.drawable.ic_panda);
        list.add(R.drawable.ic_leaf);
        PostBean item1 = new PostBean(list.subList(0, 1));
        PostBean item2 = new PostBean(list.subList(0, 2));
        PostBean item3 = new PostBean(list.subList(0, 3));
        PostBean item4 = new PostBean(list.subList(0, 4));
        PostBean item5 = new PostBean(list.subList(0, 5));
        PostBean item6 = new PostBean(list.subList(0, 6));
        PostBean item7 = new PostBean(list.subList(0, 7));
        PostBean item8 = new PostBean(list.subList(0, 8));
        PostBean item9 = new PostBean(list.subList(0, 9));
        List<PostBean> data = new ArrayList<>();
        data.add(item1);
        data.add(item2);
        data.add(item3);
        data.add(item4);
        data.add(item5);
        data.add(item6);
        data.add(item7);
        data.add(item8);
        data.add(item9);
        MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
