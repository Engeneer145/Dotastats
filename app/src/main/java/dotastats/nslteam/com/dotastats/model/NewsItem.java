package dotastats.nslteam.com.dotastats.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dmyroromaniuk on 17.06.16.
 */
public class NewsItem {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("contents")
    @Expose
    private String contents;

    public NewsItem(String title, String url, String contents) {
        this.title = title;
        this.url = url;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return title + " "
                + url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getContents() {
        return contents;
    }
}
