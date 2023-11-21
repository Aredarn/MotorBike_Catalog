package com.example.motorbike_catalog;

// Manufacturer.java
public class Manufacturer {
    private String name;
    private String Location;
    private int FoundationYear;

    public Manufacturer(String name,String location,int FoundationYear) {
        this.name = name; this.Location = location;this.FoundationYear = FoundationYear;
    }

    public String getName() {
        return name;
    }
    public String getLocation(){return Location;}

    public int getFoundationYear(){return FoundationYear;}

}
