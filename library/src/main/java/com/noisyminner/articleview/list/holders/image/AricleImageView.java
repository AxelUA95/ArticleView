package com.noisyminner.articleview.list.holders.image;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.noisyminner.articleview.R;
import com.noisyminner.articleview.list.holders.BaseView;
import com.noisyminner.articleview.model.ModelDimens;

/**
 * created by Alex Ivanov on 11/11/16.
 */

class AricleImageView extends BaseView {

    private ImageView imageView;

    public AricleImageView(Context context) {
        super(context);
        inflateAndAttach(context);
    }

    public AricleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateAndAttach(context);
    }

    public AricleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateAndAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AricleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateAndAttach(context);
    }

    private void inflateAndAttach(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.holder_image, this, true);
        imageView = (ImageView) v.findViewById(R.id.img_data);
    }

    @Override
    public void setData(String data, @Nullable ModelDimens dimens) {
        Context context = getContext();
        Glide.with(context).load(data).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        if (dimens != null) {
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            float scale = (float) screenWidth / dimens.getWidth();
            setLayoutParams(new LinearLayout.LayoutParams(
                    screenWidth, (int) (scale * dimens.getHeight())));
        } else {
            float height = getContext().getResources().getDimension(R.dimen.default_height);
            setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) height));
        }
    }
}
