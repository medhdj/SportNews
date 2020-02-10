package com.medhdj.samples.data;

import com.medhdj.samples.data.entity.NewsEntity;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

import static com.medhdj.samples.data.entity.NewsEntity.NEWS_ENTITY_TYPE.STORY;
import static com.medhdj.samples.data.entity.NewsEntity.NEWS_ENTITY_TYPE.VIDEO;

public class TestData {

    public final static List<NewsEntity> newsList = Arrays.asList(
            //timestamp 23:00
            new NewsEntity(STORY, 1581202800, "STR1", "IMGURL1", "CAT1",
                    null, -1, "INTRO1", "CONTENT1", "AUTHOR1"),

            //timestamp 23:20
            new NewsEntity(VIDEO, 1581204000, "VID1", "IMGURL1", "CAT1",
                    "VIDURL1", 10, null, null, null)
            );
    public static Observable newsListObservable = Observable.just(newsList);
}
