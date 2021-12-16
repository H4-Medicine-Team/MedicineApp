package com.ds.nofication.Models.Backend;

public class Drug {
    private String name;
    private String identifier;

    public Drug(String name, String identifier){
        this.name = name;
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return identifier;
    }
}
