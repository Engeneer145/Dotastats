package dotastats.nslteam.com.dotastats.api;

import java.util.List;

import dotastats.nslteam.com.dotastats.model.AppNews;
import dotastats.nslteam.com.dotastats.model.NewsItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dmyroromaniuk on 17.06.16.
 */
public interface SteamStatsClient {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<AppNews>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
