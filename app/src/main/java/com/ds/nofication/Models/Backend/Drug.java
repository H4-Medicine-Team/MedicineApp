package com.ds.nofication.Models.Backend;

public class Drug {
    /**
     * The name of the drug
     */
    private String name;

    /**
     * The unique id which identifies a drug.
     */
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
