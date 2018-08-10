package com.icool.ngl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.icool.ngl.adapter.MyAdapter;
import com.icool.ngl.bean.PostBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/8/9.
 */
public class RecyclerViewActivity extends AppCompatActivity {

    //图片来自花瓣网  http://huaban.com/ 侵删
    private String[] urls = new String[]{
            "http://api.meisupic.com/getImg.php?imgurl=https%3A%2F%2Fst3.depositphotos.com%2Fthumbs%2F3480661%2Fimage%2F18237%2F182379928%2Fapi_thumb_450.jpg&id=182379928&userid=3480661&imgfile=thumbs",
            "http://api.meisupic.com/getImg.php?imgurl=https%3A%2F%2Fst.depositphotos.com%2Fthumbs%2F2466713%2Fvector%2F4199%2F41993079%2Fapi_thumb_450.jpg&id=41993079&userid=2466713&imgfile=thumbs",
            "http://api.meisupic.com/getImg.php?imgurl=https%3A%2F%2Fst3.depositphotos.com%2Fthumbs%2F1008889%2Fvector%2F12796%2F127962690%2Fapi_thumb_450.jpg&id=127962690&userid=1008889&imgfile=thumbs",
            "http://api.meisupic.com/getImg.php?imgurl=https%3A%2F%2Fstatic6.depositphotos.com%2Fthumbs%2F1011278%2Fvector%2F654%2F6546799%2Fapi_thumb_450.jpg&id=6546799&userid=1011278&imgfile=thumbs",
            "http://api.meisupic.com/getImg.php?imgurl=https%3A%2F%2Fst.depositphotos.com%2Fthumbs%2F2707583%2Fvector%2F3261%2F32611085%2Fapi_thumb_450.jpg&id=32611085&userid=2707583&imgfile=thumbs",
            "http://api.meisupic.com/getImg.php?imgurl=https%3A%2F%2Fst.depositphotos.com%2Fthumbs%2F2466713%2Fvector%2F3813%2F38133279%2Fapi_thumb_450.jpg&id=38133279&userid=2466713&imgfile=thumbs",
            "http://api.meisupic.com/getImg.php?imgurl=https%3A%2F%2Fst2.depositphotos.com%2Fthumbs%2F5638420%2Fvector%2F8268%2F82686578%2Fapi_thumb_450.jpg&id=82686578&userid=5638420&imgfile=thumbs",
            "http://api.meisupic.com/getImg.php?imgurl=https%3A%2F%2Fst.depositphotos.com%2Fthumbs%2F2466713%2Fvector%2F3340%2F33401009%2Fapi_thumb_450.jpg&id=33401009&userid=2466713&imgfile=thumbs",
            "http://api.meisupic.com/getImg.php?imgurl=https%3A%2F%2Fst.depositphotos.com%2Fthumbs%2F2466713%2Fvector%2F3813%2F38133299%2Fapi_thumb_450.jpg&id=38133299&userid=2466713&imgfile=thumbs",
            "http://api.meisupic.com/getImg.php?imgurl=https%3A%2F%2Fst2.depositphotos.com%2Fthumbs%2F1028735%2Fvector%2F11426%2F114266248%2Fapi_thumb_450.jpg&id=114266248&userid=1028735&imgfile=thumbs"
    };

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
        List<String> list = new ArrayList<>();

        ////////////////////////////
        list.addAll(Arrays.asList(urls));
        //1、4、7 第一个为长图
        PostBean item1 = new PostBean(list.subList(0, 1));
        PostBean item2 = new PostBean(list.subList(1, 3));
        PostBean item3 = new PostBean(list.subList(1, 4));
        PostBean item4 = new PostBean(list.subList(0, 4));
        PostBean item5 = new PostBean(list.subList(1, 6));
        PostBean item6 = new PostBean(list.subList(1, 7));
        PostBean item7 = new PostBean(list.subList(0, 7));
        PostBean item8 = new PostBean(list.subList(1, 9));
        PostBean item9 = new PostBean(list.subList(1, 10));
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
        ///////////////
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
