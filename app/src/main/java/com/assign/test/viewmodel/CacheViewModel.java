package com.assign.test.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.assign.test.Model.ArticleModel;
import com.assign.test.repo.CacheRepository;
import com.assign.test.repo.room.AppDatabase;
import com.assign.test.repo.room.AppDatabase;
import com.assign.test.utils.SharedPrefUtils;

import java.util.List;

public class CacheViewModel extends ViewModel {


  private AppDatabase appDatabase;
    private CacheRepository cacheRepository;
    private SharedPrefUtils sharedPrefUtils;
    private LiveData<List<ArticleModel>> articleLiveData;

    public CacheViewModel( SharedPrefUtils sharedPrefUtils,AppDatabase appDatabase) {
        this.sharedPrefUtils = sharedPrefUtils;
        this.appDatabase = appDatabase;

        articleLiveData = CacheRepository.getInstance().getAllArticles(appDatabase, sharedPrefUtils);
    }

    public LiveData<List<ArticleModel>> getAllArticles(){
        return  articleLiveData;
    }
}
