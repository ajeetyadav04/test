package com.assign.test.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.assign.test.utils.ApiConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "article_table")
public class ArticleModel{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private transient int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName(ApiConstant.AUTHOR)
    @Expose(serialize = true, deserialize = false)
    private String author;
    @SerializedName(ApiConstant.TITLE)
    @Expose(serialize = true, deserialize = false)
    private String title;
    @SerializedName(ApiConstant.DESCRIPTION)
    @Expose(serialize = true, deserialize = false)
    private String description;
    @SerializedName(ApiConstant.PUBLISH_AT)
    @Expose(serialize = true, deserialize = false)
    private String publishAt;
    @SerializedName(ApiConstant.CONTENT)
    @Expose(serialize = true, deserialize = false)
    private String content;
    @SerializedName(ApiConstant.URL)
    @Expose(serialize = true, deserialize = false)
    private String url;
    @SerializedName(ApiConstant.IMAGE_URL)
    @Expose(serialize = true, deserialize = false)
    private String imageUrl;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(String publishAt) {
        this.publishAt = publishAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
