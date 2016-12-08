package com.noisyminner.articleview.list.holders.video;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.devbrackets.android.exomedia.listener.OnCompletionListener;
import com.devbrackets.android.exomedia.listener.OnErrorListener;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.noisyminner.articleview.R;
import com.noisyminner.articleview.list.holders.BaseView;
import com.noisyminner.articleview.model.ModelDimens;

/**
 * created by Alex Ivanov on 11/11/16.
 */

public class VideoView extends BaseView implements OnPreparedListener, OnCompletionListener, OnErrorListener, View.OnClickListener {

    private EMVideoView videoView;
    private ImageView reloadImageView;
    private View progressBar;

    private String data;
    private boolean isPlaying = false;
    private boolean pauseFromTouch = false;

    public VideoView(Context context) {
        super(context);
        inflateAndAttach(context);
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateAndAttach(context);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateAndAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateAndAttach(context);
    }

    private void inflateAndAttach(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.holder_video, this, true);
        videoView = (EMVideoView) v.findViewById(R.id.video_view);
        reloadImageView = (ImageView) v.findViewById(R.id.img_reload);
        progressBar = v.findViewById(R.id.progress_video);
        reloadImageView.setOnClickListener(this);
        videoView.setOnClickListener(this);
    }

    @Override
    public void setData(String data, @Nullable ModelDimens dimens) {
        if (videoView == null) return;
        this.data = data;

        videoView.setOnPreparedListener(this);
        videoView.setOnCompletionListener(this);
        videoView.setOnErrorListener(this);

        videoView.setVideoURI(Uri.parse(data));

        if (dimens != null) {
            int screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
            float scale = (float) screenWidth / dimens.getWidth();
            setLayoutParams(new LinearLayout.LayoutParams(screenWidth, (int) (scale * dimens.getHeight())));
        }
    }

    @Override
    public void onPrepared() {
        if (videoView != null) {
            progressBar.setVisibility(View.GONE);
            reloadImageView.setVisibility(View.GONE);
            if (isPlaying) videoView.start();
        }
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
        if (isPlaying) {
            if (pauseFromTouch) return;
            videoView.start();
        } else {
            videoView.pause();
        }
    }

    private void changePauseFromTouch() {
        if (reloadImageView.getVisibility() != GONE) {
            onClick(reloadImageView);
            return;
        }
        pauseFromTouch = !pauseFromTouch;
        if (pauseFromTouch) {
            videoView.pause();
        } else {
            videoView.start();
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public void onClick(View view) {
        if (view == reloadImageView) {
            progressBar.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);

            videoView.setOnPreparedListener(this);
            videoView.setOnCompletionListener(this);
            videoView.setOnErrorListener(this);

            videoView.setVideoURI(Uri.parse(data));
        } else if (videoView == view) {
            changePauseFromTouch();
        }
    }

    @Override
    public void onCompletion() {
        progressBar.setVisibility(View.GONE);
        reloadImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onError() {
        onCompletion();
        return false;
    }
}
