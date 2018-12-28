package com.example.a32672.myapplication;

/**
 * Created by 32672 on 2018/12/26.
 */

public class MainItemBean {
    public int imageId;
    public String title;

    public MainItemBean(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
