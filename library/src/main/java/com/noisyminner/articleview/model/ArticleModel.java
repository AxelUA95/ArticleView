package com.noisyminner.articleview.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.noisyminner.articleview.constants.ModelTypes;

/**
 * created by Alex Ivanov on 11/11/16.
 */

public class ArticleModel implements Parcelable {

    private int pos;
    private int type;
    private String data;
    private ModelDimens dimens;

    public ArticleModel(int pos, int type, String data, int width, int height) {
        this.pos = pos;
        this.type = type;
        if (!ModelTypes.DEFAULT_TYPES.contains(type)) {
            this.type += ModelTypes.USER_TYPES;
        }
        this.data = data;
        if (width != 0 && height != 0) {
            this.dimens = new ModelDimens(width, height);
        }
    }

    private ArticleModel(Parcel in) {
        pos = in.readInt();
        type = in.readInt();
        data = in.readString();
        dimens = in.readParcelable(ModelDimens.class.getClassLoader());
    }

    public static final Creator<ArticleModel> CREATOR = new Creator<ArticleModel>() {
        @Override
        public ArticleModel createFromParcel(Parcel in) {
            return new ArticleModel(in);
        }

        @Override
        public ArticleModel[] newArray(int size) {
            return new ArticleModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(pos);
        parcel.writeInt(type);
        parcel.writeString(data);
        parcel.writeParcelable(dimens, i);
    }

    //GETTERS
    public int getPos() {
        return pos;
    }

    public int getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public ModelDimens getDimens() {
        return dimens;
    }
}
