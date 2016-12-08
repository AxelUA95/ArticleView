package com.noisyminner.articleview;

import android.content.Context;

import com.noisyminner.articleview.list.holders.BaseView;

/**
 * created by Alex Ivanov on 11/11/16.
 */

public interface Creator {
    BaseView createInstance(Context context);
}
