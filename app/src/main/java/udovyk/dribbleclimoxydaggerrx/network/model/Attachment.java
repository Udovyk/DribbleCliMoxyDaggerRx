package udovyk.dribbleclimoxydaggerrx.network.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Attachment implements Serializable {

    @SerializedName("id")
    private long id;

    @SerializedName("url")
    private String url;

    @SerializedName("thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("size")
    private long size;

    @SerializedName("content_type")
    private String contentType;

    @SerializedName("views_count")
    private int viewsCount;

    @SerializedName("created_at")
    private Date createdAt;

    public boolean isImage() {
        return contentType.equals(MimeType.JPEG.toString())
                || contentType.equals(MimeType.PNG.toString())
                || contentType.equals(MimeType.GIF.toString())
                || contentType.equals(MimeType.BMP.toString())
                || contentType.equals(MimeType.WEBP.toString());
    }

    public boolean isGif() {
        return contentType.equals(MimeType.GIF.toString());
    }

    public boolean isAudio() {
        return contentType.contains(MimeType.AUDIO.toString());
    }

    public boolean isVideo() {
        return contentType.equals(MimeType.MPEG.toString())
                || contentType.equals(MimeType.MP4.toString())
                || contentType.equals(MimeType.QUICKTIME.toString())
                || contentType.equals(MimeType.THREEGPP.toString())
                || contentType.equals(MimeType.THREEGPP2.toString())
                || contentType.equals(MimeType.MKV.toString())
                || contentType.equals(MimeType.WEBM.toString())
                || contentType.equals(MimeType.TS.toString())
                || contentType.equals(MimeType.AVI.toString());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
