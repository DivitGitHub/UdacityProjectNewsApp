package com.divitngoc.android.udacityprojectnewsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by DxAlchemistv1 on 06/05/2017.
 */

public class NewsArrayAdapter extends ArrayAdapter<News> {

    private static final String LOG_TAG = NewsArrayAdapter.class.getSimpleName();

    public NewsArrayAdapter(Context context, List<News> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Get the {@link News} object located at this position in the list
        final News currentNews = getItem(position);

        TextView section = (TextView) listItemView.findViewById(R.id.section);
        section.setText(currentNews.getSection());

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentNews.getTitle());

        TextView webPublicationDate = (TextView) listItemView.findViewById(R.id.webPublicationDate);
        String stringWebDate = currentNews.getWebPublicationDate();

        String desiredFormattedDate = formattedDate(stringWebDate);
        webPublicationDate.setText(desiredFormattedDate);

        return listItemView;
    }

    private static String formattedDate(String stringWebDate) {
        SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat desiredDateFormat = new SimpleDateFormat("MMMM dd yyyy HH:mm");
        String strDesiredDateFormat = "";
        try {
            Date date = currentDateFormat.parse(stringWebDate);
            strDesiredDateFormat = desiredDateFormat.format(date);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Problem parsing date format", e);
        }
        return  strDesiredDateFormat;
    }

}
