package com.divitngoc.android.udacityprojectnewsapp;

/**
 * Created by DxAlchemistv1 on 06/05/2017.
 */

public class News {
    private String section;
    private String title;
    private String webPublicationDate;
    private String webUrl;

    public News(String section, String title, String webPublicationDate, String webUrl) {
        this.section = section;
        this.title = title;
        this.webPublicationDate = webPublicationDate;
        this.webUrl = webUrl;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
