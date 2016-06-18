package dotastats.nslteam.com.dotastats.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dmyroromaniuk on 18.06.16.
 */
public class AppNews {

    @SerializedName("appid")
    @Expose
    private String appId;
    @SerializedName("newsitems")
    @Expose
    private List<NewsItem> newsItems;

    public AppNews(String appId, List<NewsItem> newsitems) {
        this.appId = appId;
        this.newsItems = newsitems;

    }

    public String getAppId() {
        return appId;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }
}
