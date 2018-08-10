package com.icool.ngl.bean;

import java.util.List;

/**
 * @author zhzy
 * @Description Created by ZhaoZongyao on 2018/8/9.
 */
public class PostBean {

    private String title = "Sooner";
    private String content = "Sooner or later， we will be passed One people, one city, a lifetime love dearly.";
    private List<Integer> resIds;
    private List<String> imgUrls;

    public PostBean(List<String> imgUrls) {
        this.imgUrls = imgUrls;
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

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }
}
