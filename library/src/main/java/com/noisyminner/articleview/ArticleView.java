package com.noisyminner.articleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.noisyminner.articleview.constants.ModelTypes;
import com.noisyminner.articleview.list.holders.BaseView;
import com.noisyminner.articleview.list.holders.video.VideoView;
import com.noisyminner.articleview.model.ArticleModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * created by Alex Ivanov on 11/11/16.
 */

public class ArticleView extends LinearLayout implements OnScrollChangeListener {

    static OnLinkClick onLinkClickCallback;

    private final List<ArticleModel> articleList = new ArrayList<>();

    public ArticleView(Context context) {
        super(context);
        initView();
    }

    public ArticleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ArticleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
    }

    public void setData(List<ArticleModel> list, @Nullable OnLinkClick onLinkClick) {
        onLinkClickCallback = onLinkClick;

        Collections.sort(list, new Comparator<ArticleModel>() {
            @Override
            public int compare(ArticleModel articleModel, ArticleModel t1) {
                int pos1 = articleModel.getPos();
                int pos2 = t1.getPos();
                if (pos1 == pos2) {
                    return 0;
                } else if (pos1 > pos2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        if (!articleList.isEmpty()) articleList.clear();
        articleList.addAll(list);
        updateAllViews();
    }

    public void setData(List<ArticleModel> list) {
        setData(list, null);
    }

    private void updateAllViews() {
        removeAllViews();
        Context context = getContext();
        for (ArticleModel model : articleList) {
            BaseView view = TypesMapper.getViewHolderForId(context, model.getType());
            view.setData(model.getData(), model.getDimens());
            addView(view);
        }
    }

    public void setScrollView(NestedScrollView scrollView) {
        scrollView.setOnScrollChangeListener(this);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int size = articleList.size();
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        for (int i = 0; i < size; i++) {
            ArticleModel model = articleList.get(i);
            if (model.getType() != ModelTypes.VIDEO) {
                continue;
            }
            VideoView videoView = (VideoView) getChildAt(i);
            boolean isPlaying = videoView.isPlaying();
            float y = videoView.getY();
            if (y > scrollY && y < scrollY + screenHeight) {
                if (isPlaying) continue;
                videoView.setPlaying(true);
            } else {
                if (!isPlaying) continue;
                videoView.setPlaying(false);
            }
        }
    }

    public interface OnLinkClick {
        void onLinkClicked(String url);
    }
}
