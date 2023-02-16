package com.example.busbuddydemo;

public class Data_For_Bus {

    private String placeName;
    private String firstShift;
    private String secondShift;

    public Data_For_Bus(  String placeName , String firstShift ,String secondShift){
        this.placeName = placeName;
        this.firstShift = firstShift;
        this.secondShift = secondShift;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getFirstShift() {
        return firstShift;
    }

    public void setFirstShift(String firstShift) {
        this.firstShift = firstShift;
    }

    public String getSecondShift() {
        return secondShift;
    }

    public void setSecondShift(String secondShift) {
        this.secondShift = secondShift;
    }
}
