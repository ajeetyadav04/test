package com.assign.test.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefUtils {
    private Context mContext;
    public static final String FIRST_TIME="first_time";
    private static SharedPrefUtils mInstance;
    private SharedPrefUtils(Context context) {
        mContext = context;
    }
    public static SharedPrefUtils getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefUtils(context);
        }
        return mInstance;
    }
    public void setArticleExits() {
        if (mContext != null) {
            SharedPreferences appInstallInfoSharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
            SharedPreferences.Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
            appInstallInfoEditor.putString(FIRST_TIME, "YES");
            appInstallInfoEditor.commit();
        }
    }
    public boolean isAtricleExits() {
        String s=null;
        SharedPreferences appInstallInfoSharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        if (appInstallInfoSharedPref.contains(FIRST_TIME)) {
            s = appInstallInfoSharedPref.getString(FIRST_TIME, "");
        }
        return s=="YES";
    }

}
