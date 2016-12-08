package com.noisyminner.articleview.list.holders.text;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.noisyminner.articleview.R;
import com.noisyminner.articleview.TextUtils;
import com.noisyminner.articleview.list.holders.BaseView;
import com.noisyminner.articleview.model.ModelDimens;

/**
 * created by Alex Ivanov on 11/11/16.
 */

class ArticleTextView extends BaseView {

    private TextView markdownView;

    public ArticleTextView(Context context) {
        super(context);
        inflateAndAttach(context);
    }

    public ArticleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateAndAttach(context);
    }

    public ArticleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateAndAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ArticleTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateAndAttach(context);
    }

    private void inflateAndAttach(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.holder_text, this, true);
        markdownView = (TextView) v.findViewById(R.id.markdown_data);
    }

    @Override
    public void setData(String data, @Nullable ModelDimens dimens) {
        if (markdownView != null) {
            markdownView.setText(TextUtils.getFromMarkDown(data));
            markdownView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
