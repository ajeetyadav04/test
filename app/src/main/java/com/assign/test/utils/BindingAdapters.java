package com.assign.test.utils;

import android.databinding.BindingAdapter;
import android.view.View;

public class BindingAdapters {

    @BindingAdapter("setVisibleGone")
    public static void setVisibleGone(View view, boolean visible) {
        if (visible)
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.GONE);
    }
}
