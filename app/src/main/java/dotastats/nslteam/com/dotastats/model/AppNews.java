package dotastats.nslteam.com.dotastats.model;

import java.util.List;

/**
 * Created by dmyroromaniuk on 18.06.16.
 */
public class AppNews {

    private List<NewsItem> newsitems;

    public AppNews(List<NewsItem> newsitems) {
        this.newsitems = newsitems;

    }

    public List<NewsItem> getNewsitems() {
        return newsitems;
    }
}
