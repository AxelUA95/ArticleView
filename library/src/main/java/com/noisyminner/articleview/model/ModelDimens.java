package com.noisyminner.articleview.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * created by Alex Ivanov on 11/14/16.
 */

public class ModelDimens implements Parcelable {

    private final int width;
    private final int height;

    public ModelDimens(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private ModelDimens(Parcel in) {
        width = in.readInt();
        height = in.readInt();
    }

    public static final Creator<ModelDimens> CREATOR = new Creator<ModelDimens>() {
        @Override
        public ModelDimens createFromParcel(Parcel in) {
            return new ModelDimens(in);
        }

        @Override
        public ModelDimens[] newArray(int size) {
            return new ModelDimens[size];
        }
    };

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(width);
        parcel.writeInt(height);
    }
}
