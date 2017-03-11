package ileu.biz.fragmentprj.models;

import com.google.gson.annotations.SerializedName;

import java.util.StringTokenizer;

/**
 * Created by Murager on 3/11/17.
 */

public class Photo {

    @SerializedName("albumId")
    private int albumId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    public String imageUrlLastString() {
        StringTokenizer token = new StringTokenizer(url, "/");
        String url = null;
        while (token.hasMoreElements()) {
            url = token.nextToken();
        }
        return url;
    };


    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Photo{");
        sb.append("albumId=").append(albumId);
        sb.append(", id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", thumbnailUrl='").append(thumbnailUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
