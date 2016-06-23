package dotastats.nslteam.com.dotastats.api;


import dotastats.nslteam.com.dotastats.model.NewsResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dmyroromaniuk on 17.06.16.
 */
public interface SteamStatsClient {
    @GET("/ISteamNews/GetNewsForApp/v0002/?format=json")
    Observable<NewsResponse> getAll(
            @Query("appid") String appId
    );
}
