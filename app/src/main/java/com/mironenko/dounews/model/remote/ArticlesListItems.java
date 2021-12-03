package com.mironenko.dounews.model.remote;

import android.media.Image;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Url;

public class ArticlesListItems {
    private int id;
//    @SerializedName("title")
    private String title;
    private Url url;
    private String category;
    private String category_url;
    private String announcement;
    private String tags;
    private int pageviews;
    private int comments_count;
    private Image img_big;
    private Image img_big_2x;
    private Image img_small;
    private Image img_small_2x;
    private String author_name;
    private Url author_url;
    private String published;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory_url() {
        return category_url;
    }

    public void setCategory_url(String category_url) {
        this.category_url = category_url;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getPageviews() {
        return pageviews;
    }

    public void setPageviews(int pageviews) {
        this.pageviews = pageviews;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public Image getImg_big() {
        return img_big;
    }

    public void setImg_big(Image img_big) {
        this.img_big = img_big;
    }

    public Image getImg_big_2x() {
        return img_big_2x;
    }

    public void setImg_big_2x(Image img_big_2x) {
        this.img_big_2x = img_big_2x;
    }

    public Image getImg_small() {
        return img_small;
    }

    public void setImg_small(Image img_small) {
        this.img_small = img_small;
    }

    public Image getImg_small_2x() {
        return img_small_2x;
    }

    public void setImg_small_2x(Image img_small_2x) {
        this.img_small_2x = img_small_2x;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Url getAuthor_url() {
        return author_url;
    }

    public void setAuthor_url(Url author_url) {
        this.author_url = author_url;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }
}
