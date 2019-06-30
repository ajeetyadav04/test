package com.assign.test.repo;

import androidx.lifecycle.LiveData;

import com.assign.test.Model.ArticleModel;
import com.assign.test.baseview.BaseModel;
import com.assign.test.repo.retrofit.ApiClient;
import com.assign.test.repo.retrofit.ResponseCallbackHandler;
import com.assign.test.repo.room.AppDatabase;
import com.assign.test.utils.SharedPrefUtils;

import java.util.List;

public class CacheRepository {
    private static CacheRepository cacheRepository = new CacheRepository();

    private LiveData<List<ArticleModel>> articleListLiveData;

    public static CacheRepository getInstance() {
        return cacheRepository;
    }

    public LiveData<List<ArticleModel>> getAllArticles(AppDatabase appDatabase, SharedPrefUtils sharedPrefUtils) {

        if (!sharedPrefUtils.isAtricleExits()) {

            ApiClient.getInstance().getApiService().getArticleList().enqueue(new ResponseCallbackHandler<BaseModel<List<ArticleModel>>>() {
                @Override
                protected void handleSuccess(BaseModel<List<ArticleModel>> data) {
                    new Thread(
                            () -> {


                                appDatabase.articleDao().insertAll(data.getArticles());
                                sharedPrefUtils.setArticleExits();

                            }).start();
                }

                @Override
                protected void handleFailure(BaseModel error) {

                }

                @Override
                protected void tokenRefreshed() {

                }
            });

        }
        return appDatabase.articleDao().getAll();
    }
}
