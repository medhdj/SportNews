package com.medhdj.samples.sportnews.common;

import com.medhdj.samples.domain.usecase.BaseUseCase;
import com.medhdj.samples.domain.usecase.LoadNewsFeed;
import com.medhdj.samples.sportnews.ui.home.HomeViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    BaseUseCase useCase;

    public ViewModelFactory(BaseUseCase useCase) {
        this.useCase = useCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel((LoadNewsFeed) useCase);
        } else {
            return null;
        }

    }
}

