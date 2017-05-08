package com.divitngoc.android.udacityprojectnewsapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by DxAlchemistv1 on 06/05/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public NewsLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null || TextUtils.isEmpty(mUrl)) {
            return null;
        }

        List<News> newsList = QueryUtils.fetchNewsData(mUrl);
        return newsList;
    }
}
