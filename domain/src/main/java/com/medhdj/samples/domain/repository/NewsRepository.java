package com.medhdj.samples.domain.repository;

import com.medhdj.samples.domain.model.News;

import java.util.List;

import io.reactivex.Observable;


public interface NewsRepository {
    Observable<List<News>> news();
}
