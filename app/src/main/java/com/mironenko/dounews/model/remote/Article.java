package com.mironenko.dounews.model.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("category_url")
    @Expose
    private String categoryUrl;
    @SerializedName("announcement")
    @Expose
    private String announcement;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("pageviews")
    @Expose
    private Integer pageviews;
    @SerializedName("comments_count")
    @Expose
    private Integer commentsCount;
    @SerializedName("img_big")
    @Expose
    private String imgBig;
    @SerializedName("img_big_2x")
    @Expose
    private String imgBig2x;
    @SerializedName("img_small")
    @Expose
    private String imgSmall;
    @SerializedName("img_small_2x")
    @Expose
    private String imgSmall2x;
    @SerializedName("author_name")
    @Expose
    private String authorName;
    @SerializedName("author_url")
    @Expose
    private String authorUrl;
    @SerializedName("published")
    @Expose
    private String published;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
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

    public Integer getPageviews() {
        return pageviews;
    }

    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getImgBig() {
        return imgBig;
    }

    public void setImgBig(String imgBig) {
        this.imgBig = imgBig;
    }

    public String getImgBig2x() {
        return imgBig2x;
    }

    public void setImgBig2x(String imgBig2x) {
        this.imgBig2x = imgBig2x;
    }

    public String getImgSmall() {
        return imgSmall;
    }

    public void setImgSmall(String imgSmall) {
        this.imgSmall = imgSmall;
    }

    public String getImgSmall2x() {
        return imgSmall2x;
    }

    public void setImgSmall2x(String imgSmall2x) {
        this.imgSmall2x = imgSmall2x;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

}
