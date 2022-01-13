package com.ds.nofication.Models.Backend;

public class MedicineDkDTO {
    private String[] htmlData;
    private String title;

    public MedicineDkDTO(String[] htmlData, String title)
    {
        this.htmlData = htmlData;
        this.title = title;
    }
    public String[] getHtmlData() {
        return htmlData;
    }

    public String getTitle() {
        return title;
    }
}
