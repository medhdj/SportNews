package com.medhdj.samples.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.medhdj.samples.data.entity.NewsEntity;
import com.medhdj.samples.data.network.NewsFeedRestApi;

import java.lang.reflect.Type;
import java.util.List;

public class NewsEntityJsonMapper {
    private final Gson gson;

    public NewsEntityJsonMapper(Gson gson) {
        this.gson = gson;
    }

    public List<NewsEntity> transformUserEntityCollection(String newsFeedApiJsonString)
            throws JsonSyntaxException {
        final Type apiResponse = new TypeToken<NewsFeedRestApi>() {
        }.getType();
        return this.gson.fromJson(newsFeedApiJsonString, apiResponse);
    }
}
