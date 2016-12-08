package com.noisyminner.articleview;

import android.content.Context;
import android.util.SparseArray;

import com.noisyminner.articleview.constants.ModelTypes;
import com.noisyminner.articleview.list.holders.BaseView;
import com.noisyminner.articleview.list.holders.defaultHolder.DefaultCreator;
import com.noisyminner.articleview.list.holders.image.ImageCreator;
import com.noisyminner.articleview.list.holders.text.ArticleViewCreator;
import com.noisyminner.articleview.list.holders.video.VideoCreator;

/**
 * created by Alex Ivanov on 11/11/16.
 */

public class TypesMapper {

    private static final SparseArray<Creator> creatorMap = new SparseArray<>();

    static {
        creatorMap.put(ModelTypes.TEXT, new ArticleViewCreator());
        creatorMap.put(ModelTypes.IMAGE, new ImageCreator());
        creatorMap.put(ModelTypes.VIDEO, new VideoCreator());
    }

    public static void addNewTypes(int typeId, Creator creator) {
        creatorMap.put(typeId + ModelTypes.USER_TYPES, creator);
    }

    public static BaseView getViewHolderForId(Context context, int id) {
        Creator creator = creatorMap.get(id);
        if (creator != null) {
            return creator.createInstance(context);
        } else {
            return new DefaultCreator().createInstance(context);
        }
    }
}
