package com.divitngoc.android.udacityprojectnewsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final int NEWS_LOADER_ID = 0;

    //URL parameters and values to retrieve dataset from theguardian api
    private static final String BASE_URL = "https://content.guardianapis.com/search?";
    private static final String ORDER_BY_PARAMETER = "order-by";
    private static final String ORDER_BY_NEWEST_VALUE = "newest";
    private static final String QUERY_PARAMETER = "q";
    private static final String QUERY_TECHNOLOGY_VALUE = "technology";
    private static final String END_URL_PARAMETER = "api-key";
    private static final String END_URL_VALUE = "test";

    private NewsArrayAdapter newsArrayAdapter;

    private LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsArrayAdapter = new NewsArrayAdapter(this, new ArrayList<News>());

        TextView emptyView = (TextView) findViewById(R.id.empty_state_view);

        ListView list = (ListView) findViewById(R.id.list);
        list.setEmptyView(emptyView);
        list.setAdapter(newsArrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = newsArrayAdapter.getItem(position);

                String webUrl = currentNews.getWebUrl();

                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
                startActivity(webIntent);
            }
        });

        if (!isConnected()) {
            emptyView.setText(getString(R.string.no_connection));
        }

        loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(NEWS_LOADER_ID, null, this);

    }

    private boolean isConnected() {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {

        Uri baseUrl = Uri.parse(BASE_URL);
        Uri.Builder uriBuilder = baseUrl.buildUpon();

        uriBuilder.appendQueryParameter(ORDER_BY_PARAMETER, ORDER_BY_NEWEST_VALUE);
        uriBuilder.appendQueryParameter(QUERY_PARAMETER, QUERY_TECHNOLOGY_VALUE);
        uriBuilder.appendQueryParameter(END_URL_PARAMETER, END_URL_VALUE);

        return new NewsLoader(MainActivity.this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        newsArrayAdapter.clear();

        if (data != null && !data.isEmpty()) {
            newsArrayAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsArrayAdapter.clear();
    }
}
