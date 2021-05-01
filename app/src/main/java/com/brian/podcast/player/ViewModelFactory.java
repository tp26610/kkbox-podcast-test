package com.brian.podcast.player;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.brian.podcast.common.UseCaseHandler;
import com.brian.podcast.player.episodes.EpisodesViewModel;
import com.brian.podcast.player.episodes.GetChannelUseCase;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final UseCaseHandler useCaseHandler;

    public ViewModelFactory(UseCaseHandler useCaseHandler) {
        this.useCaseHandler = useCaseHandler;
    }

    public <T extends ViewModel> T getViewModel(@NonNull Fragment fragment, @NonNull Class<T> viewModelClass) {
        return new ViewModelProvider(fragment, this).get(viewModelClass);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EpisodesViewModel.class)) {
            return (T) new EpisodesViewModel(useCaseHandler, provideGetChannelUseCase());
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

    private GetChannelUseCase provideGetChannelUseCase() {
        return new GetChannelUseCase();
    }
}
