package com.medhdj.samples.domain;

import com.medhdj.samples.domain.model.News;
import com.medhdj.samples.domain.model.Story;
import com.medhdj.samples.domain.model.Video;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class TestData {
    public final static List<Story> stories = Arrays.asList(
            //timestamp 23:00
            new Story(1581202800, "STR1", "URL1", "CAT1",
                    "INTRO1", "CONTENT1", "AUTHOR1"),
            //timestamp 23:30
            new Story(1581204600, "STR2", "URL2", "CAT2",
                    "INTRO2", "CONTENT2", "AUTHOR2"));

    public final static List<Video> videos = Arrays.asList(
            //timestamp 23:20
            new Video(1581204000, "VID1", "URLIMGV1",
                    "CATV1", "URLVID1", 10),
            //timestamp 23:33
            new Video(1581204780, "VID2", "URLIMGV2",
                    "CATV2", "URLVID2", 20)

    );

    public final static List<News> news = Arrays.asList(
            videos.get(1),
            stories.get(1),
            videos.get(0),
            stories.get(0));

    public final static Observable<List<Story>> storiesObservableMock = Observable.just(stories);
    public final static Observable<List<Video>> videosObservableMock = Observable.just(videos);
    public final static Observable<List<News>> newsObservableMock = Observable.just(news);
}
