package com.noisyminner.articleview.list.holders.text;

import android.content.Context;

import com.noisyminner.articleview.Creator;
import com.noisyminner.articleview.list.holders.BaseView;

/**
 * created by Alex Ivanov on 11/11/16.
 */

public class ArticleViewCreator implements Creator {
    @Override
    public BaseView createInstance(Context context) {
        return new ArticleTextView(context);
    }
}
