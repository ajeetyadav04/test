package com.assign.test.repo.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final ApiClient ourInstance = new ApiClient();
    private static Retrofit retrofit = null;
    private static final  String BASE_URL="https://newsapi.org";

    public static ApiClient getInstance() {
        return ourInstance;
    }

    public ApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)

                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
