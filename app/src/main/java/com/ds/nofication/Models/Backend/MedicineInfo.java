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
    public String getHtmlDataAsString() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < htmlData.length; i++) {
            sb.append(htmlData[i]);
        }
        String str = sb.toString();
        return str;
    }

    public String getTitle() {
        return title;
    }
}
