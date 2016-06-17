package dotastats.nslteam.com.dotastats.model;

import java.util.List;

/**
 * Created by dmyroromaniuk on 17.06.16.
 */
public class NewsItem {
    private String title;
    private String url;
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
