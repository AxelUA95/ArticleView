package com.noisyminner.articleview.list.holders.defaultHolder;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.noisyminner.articleview.list.holders.BaseView;
import com.noisyminner.articleview.model.ModelDimens;

/**
 * created by Alex Ivanov on 11/11/16.
 */

class DefaultView extends BaseView {

    public DefaultView(Context context) {
        super(context);
    }

    public DefaultView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DefaultView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setData(String data, @Nullable ModelDimens modelDimens) {

    }
}
