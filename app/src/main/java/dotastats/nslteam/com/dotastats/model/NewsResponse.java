package dotastats.nslteam.com.dotastats.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dmyroromaniuk on 18.06.16.
 */
public class NewsResponse {

    @SerializedName("appnews")
    @Expose
    private AppNews appNews;

    public NewsResponse(AppNews appNews) {
        this.appNews = appNews;
    }

    public AppNews getAppNews() {
        return appNews;
    }
}
