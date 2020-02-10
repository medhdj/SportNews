package com.medhdj.samples.sportnews.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.medhdj.samples.domain.model.News;
import com.medhdj.samples.domain.model.Story;
import com.medhdj.samples.domain.model.Video;
import com.medhdj.samples.sportnews.R;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class NewsFeedListAdapter extends RecyclerView.Adapter<NewsFeedListAdapter.NewsItemViewHolder> {

    private List<News> newsList;
    private Context context;

    RequestOptions glideRequestOptions = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL);


    public NewsFeedListAdapter(Context context) {
        this.context = context;
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        public TextView title, categories, smallTitle;
        ImageView newsImg, playImg;

        public NewsItemViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title_txt);
            smallTitle = view.findViewById(R.id.small_title_txt);
            categories = view.findViewById(R.id.categories_txt);
            newsImg = view.findViewById(R.id.news_img);
            playImg = view.findViewById(R.id.play_img);
        }
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_item, parent, false);
        return new NewsItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        News news = newsList.get(position);

        holder.title.setText(news.getTitle());
        holder.categories.setText(news.getCategory());

        boolean isVideo = News.NEWS_TYPE.VIDEO.equals(news.getNewsType());

        holder.playImg.setVisibility(isVideo ? View.VISIBLE : View.INVISIBLE);
        holder.smallTitle.setText(isVideo ? getVideoSmallText(news) : getStorySmallText(news));

        Glide.with(context).load(news.getImage())
                .apply(glideRequestOptions)
                .into(holder.newsImg);
    }

    private String getStorySmallText(News news) {
        Duration elapsedDuration = Duration.between(Instant.ofEpochSecond(news.getTimestamp()),
                Instant.now());

        String elapsedTime = DurationFormatUtils
                .formatDuration(elapsedDuration.toMillis(), "mm 'min'",
                        true);
        return context.getResources().getString(R.string.story_author, ((Story) news).getAuthor(),
                elapsedTime);
    }

    private String getVideoSmallText(News news) {
        return context.getResources().getString(R.string.video_views_count,
                ((Video) news).getViews());
    }


    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    public void notifyAdapter(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }
}