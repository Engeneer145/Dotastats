package dotastats.nslteam.com.dotastats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import dotastats.nslteam.com.dotastats.adapters.NewsAdapter;
import dotastats.nslteam.com.dotastats.api.ServiceGenerator;
import dotastats.nslteam.com.dotastats.api.SteamStatsClient;
import dotastats.nslteam.com.dotastats.model.AppNews;
import dotastats.nslteam.com.dotastats.model.NewsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private static AppNews appNews;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        getNews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getNews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceGenerator.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our Steam API interface.
        SteamStatsClient steam = retrofit.create(SteamStatsClient.class);

        Call<NewsResponse> call = steam.getAll("440");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                Log.e(TAG, "response = " + response.body().toString());

                NewsResponse news = response.body();
                if (news.getAppNews().getNewsItems() != null)
                    appNews = news.getAppNews();

                setAdapter();
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e(TAG, call.toString() + " " + t.toString());

                //Snackbar.make(view, "Yo", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });
    }


    private void setAdapter() {
        NewsAdapter mAdapter = new NewsAdapter(appNews.getNewsItems());
        RecyclerView.LayoutManager mLayoutManager;

        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);
    }

}
