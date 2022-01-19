package com.ds.nofication.Models.Backend;

import java.util.ArrayList;

public class MedicineDkDTO {
    private String id;
    private ArrayList<SpecialAttribute> specialAttributes;
    private String[] htmlData;
    private String title;

    public MedicineDkDTO(String id, ArrayList<SpecialAttribute> specialAttributes, String[] htmlData, String title)
    {
        this.id = id;
        this.specialAttributes = specialAttributes;
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
