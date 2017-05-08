package com.divitngoc.android.udacityprojectnewsapp;

/**
 * Created by DxAlchemistv1 on 06/05/2017.
 */

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving news data from theguardian.
 */
public class QueryUtils {

    //keys value for json
    private static final String RESPONSE = "response";
    private static final String RESULTS = "results";
    private static final String SECTION_NAME = "sectionName";
    private static final String WEB_TITLE = "webTitle";
    private static final String WEB_PUBLICATION_DATE = "webPublicationDate";
    private static final String WEB_URL = "webUrl";


    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
        //To prevent an instance of this object
    }

    public static List<News> fetchNewsData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
           jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "problem making http request", e);
        }

        List<News> newsList = extractNewsFromJson(jsonResponse);

        return newsList;
    }

    public static URL createUrl (String stringUrl) {
        URL url = null;
        try{
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "problem building the URL", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if(url == null) {
            return jsonResponse;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem connecting to url", e);
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder sb = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while(line != null) {
                sb.append(line);
                line = bufferedReader.readLine();
            }
        }
        return sb.toString();
    }

    private static List<News> extractNewsFromJson(String jsonResponse) {
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        List<News> newsList = new ArrayList<>();

        try {
            JSONObject baseResponse = new JSONObject(jsonResponse);
            JSONObject response = baseResponse.getJSONObject(RESPONSE);
            JSONArray resultsArray = response.getJSONArray(RESULTS);

            for(int i = 0; i < resultsArray.length(); i++) {
                JSONObject currentResults = resultsArray.getJSONObject(i);

                String sectionResponse = currentResults.getString(SECTION_NAME);
                String titleResponse = currentResults.getString(WEB_TITLE);
                String webPublicationDate = currentResults.getString(WEB_PUBLICATION_DATE);
                String webUrl = currentResults.getString(WEB_URL);

                newsList.add(new News(sectionResponse, titleResponse, webPublicationDate, webUrl));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results", e);
        }
        return newsList;
    }
}
