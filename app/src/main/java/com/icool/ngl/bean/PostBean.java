package com.icool.ngl.bean;

import java.util.List;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/8/9.
 */
public class PostBean {

    private String title="";
    private String content="";
    private List<Integer> resIds;

    public PostBean(List<Integer> resIds) {
        this.resIds = resIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getResIds() {
        return resIds;
    }

    public void setResIds(List<Integer> resIds) {
        this.resIds = resIds;
    }
}
