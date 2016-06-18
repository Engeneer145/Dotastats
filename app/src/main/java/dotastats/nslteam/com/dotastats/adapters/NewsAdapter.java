package dotastats.nslteam.com.dotastats.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dotastats.nslteam.com.dotastats.R;
import dotastats.nslteam.com.dotastats.model.NewsItem;

/**
 * Created by dmyroromaniuk on 18.06.16.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsItem> dataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsAdapter(List<NewsItem> mDataset) {
        dataset = mDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvTitle.setText(dataset.get(position).getTitle());
        holder.tvUrl.setText(dataset.get(position).getUrl().toString().substring(0, 30));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset != null ? dataset.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = (TextView) itemView.findViewById(R.id.news_title_text);
            this.tvUrl = (TextView) itemView.findViewById(R.id.news_url_text);
        }
    }
}
