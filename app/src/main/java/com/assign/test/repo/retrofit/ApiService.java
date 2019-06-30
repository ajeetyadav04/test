package com.assign.test.repo.retrofit;

import com.assign.test.Model.ArticleModel;
import com.assign.test.baseview.BaseModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    String API_VERSION = "/v2/";
    String GET_ARTICLE=API_VERSION+ "top-headlines?sources=techcrunch&apiKey=f27d3df679f64c3385dbdb7dd7969f90";

    @GET(GET_ARTICLE)
    Call<BaseModel<List<ArticleModel>>> getArticleList();



}
