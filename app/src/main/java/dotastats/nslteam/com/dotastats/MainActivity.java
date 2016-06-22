package dotastats.nslteam.com.dotastats;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import dotastats.nslteam.com.dotastats.adapters.NewsAdapter;
import dotastats.nslteam.com.dotastats.api.ServiceGenerator;
import dotastats.nslteam.com.dotastats.api.SteamStatsClient;
import dotastats.nslteam.com.dotastats.model.AppNews;
import dotastats.nslteam.com.dotastats.model.NewsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private static AppNews appNews;
    
    @BindView(R.id.main_content)
    View parentLayout;

    @BindView(R.id.news_recycler_view)
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getNews() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ServiceGenerator.API_BASE_URL)
                .build();

        SteamStatsClient steam = retrofit.create(SteamStatsClient.class);

        // RETROFIT + RX ANDROID
        /*
        Observable<NewsResponse> teamFortress = steam.getAll("440");

        teamFortress.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsResponse>() {
                    @Override
                    public void call(NewsResponse current) {
                        appNews = current.getAppNews();
                        setAdapter();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throw new OnErrorFailedException(throwable);
                    }
                }, new Action0() {
                    public void call() {
                    }
                });

        */

        // RETROFIT

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

                Snackbar.make(parentLayout, "Yo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
