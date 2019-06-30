package com.assign.test.repo.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.assign.test.Model.ArticleModel;

import java.util.List;
//
//@Dao
public interface ArticleDao {
    @Query("Select * From article_table")
    LiveData<List<ArticleModel>> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ArticleModel> articleList);

}