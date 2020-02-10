package com.medhdj.samples.data.repository.datasource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.medhdj.samples.data.entity.NewsEntity;
import com.medhdj.samples.data.entity.mapper.NewsEntityMapper;
import com.medhdj.samples.data.network.RestApiClient;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public class NewsDataSourceFactory {

    public interface NewsDataSource {
        Observable<List<NewsEntity>> retreiveNewsFeed();
    }

    private final Context context;

    public NewsDataSourceFactory(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }


    private NewsDataSource createApiDataStore() {
        return new ApiNewsDataSource(RestApiClient.getNewsFeedApi(context), new NewsEntityMapper());
    }

    private boolean isConnected() {
        boolean isConnected;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }


    public NewsDataSource createDataSource() throws UnsupportedOperationException {
        if (isConnected()) {
            return createApiDataStore();
        } else {
            //maybe a disk cache!
            throw new UnsupportedOperationException("must implement a datasource here");
        }
    }
}
