package dotastats.nslteam.com.dotastats.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dotastats.nslteam.com.dotastats.R;
import dotastats.nslteam.com.dotastats.model.NewsItem;

/**
 * Created by dmyroromaniuk on 18.06.16.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsItem> dataset;

    public NewsAdapter(List<NewsItem> mDataset) {
        dataset = mDataset;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(dataset.get(position).getTitle());
        holder.tvUrl.setText(dataset.get(position).getUrl().toString().substring(0, 30));

    }

    @Override
    public int getItemCount() {
        return dataset != null ? dataset.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_title_text)
        public TextView tvTitle;
        @BindView(R.id.news_url_text)
        public TextView tvUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
