package com.assign.test.repo.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.assign.test.Model.ArticleModel;

@Database(entities = {ArticleModel.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    public abstract ArticleDao articleDao();

}
