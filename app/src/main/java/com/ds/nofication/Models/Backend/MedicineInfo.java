package com.ds.nofication.Models.Backend;

public class MedicineInfo {
    private String[] htmlData;
    private String title;

    public MedicineInfo(String[] htmlData, String title){
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
