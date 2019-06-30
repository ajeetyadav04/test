package com.assign.test.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.assign.test.repo.room.AppDatabase;
import com.assign.test.utils.SharedPrefUtils;

public class CacheViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private SharedPrefUtils sharedPrefUtils;
    private AppDatabase appDatabase;

    public CacheViewModelFactory(SharedPrefUtils sharedPrefUtils, AppDatabase appDatabase) {
        this.sharedPrefUtils = sharedPrefUtils;
        this.appDatabase = appDatabase;
    }

    @NonNull
    @Override
    @SuppressWarnings("deprecated")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CacheViewModel(sharedPrefUtils, appDatabase);
    }
}
