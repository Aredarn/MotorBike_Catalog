package com.example.motorbike_catalog;

public class BikeModel {
    private String name;
    private String manufacturer;
    private String series;
    private int horsepower;
    private int weight;
    private int torque;
    private int firstProductionYear;
    private int lastProductionYear;
    private int ccm;
    private int noCylinders;
    private int noGears;
    private String cooling;
    private String engineType;
    private int fuelSize;
    private String fuelSystem;
    private int topSpeed;
    private String photoURL;

    // Constructor allowing null for fuelSystem
    public BikeModel(String name, String manufacturer, String series, int horsepower, int weight, int torque,
                     int firstProductionYear, int lastProductionYear, int ccm, int noCylinders, int noGears,
                     String cooling, String engineType, int fuelSize, String fuelSystem, int topSpeed,String photoURL) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.series = series;
        this.horsepower = horsepower;
        this.weight = weight;
        this.torque = torque;
        this.firstProductionYear = firstProductionYear;
        this.lastProductionYear = lastProductionYear;
        this.ccm = ccm;
        this.noCylinders = noCylinders;
        this.noGears = noGears;
        this.cooling = cooling;
        this.engineType = engineType;
        this.fuelSize = fuelSize;
        this.fuelSystem = fuelSystem;
        this.topSpeed = topSpeed;
        this.photoURL = photoURL;

    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSeries() {
        return series;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public int getWeight() {
        return weight;
    }

    public int getTorque() {
        return torque;
    }

    public int getFirstProductionYear() {
        return firstProductionYear;
    }

    public int getLastProductionYear() {
        return lastProductionYear;
    }

    public int getCcm() {
        return ccm;
    }

    public int getNoCylinders() {
        return noCylinders;
    }

    public int getNoGears() {
        return noGears;
    }

    public String getCooling() {
        return cooling;
    }

    public String getEngineType() {
        return engineType;
    }

    public int getFuelSize() {
        return fuelSize;
    }

    public String getFuelSystem() {
        return fuelSystem;
    }

    public  int getTopSpeed(){
        return topSpeed;
    }

    public String getPhotoURL(){return photoURL;}
}
