package dotastats.nslteam.com.dotastats.api;

import dotastats.nslteam.com.dotastats.model.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dmyroromaniuk on 17.06.16.
 */
public interface SteamStatsClient {
    @GET("/ISteamNews/GetNewsForApp/v0002/?format=json")
    Call<NewsResponse> getAll(
            @Query("appid") String appId
    );
}
